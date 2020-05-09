package ddvudo.WebComponent.Service.ServicesImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ddvudo.GlobalUtils.Global;
import ddvudo.ORM.Mapper.CommunityMapper;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import ddvudo.ORM.POJO.District;
import ddvudo.WebComponent.Service.Services.CommunityService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("CommunityService")
@Transactional
public class CommunityServiceImpl implements CommunityService {
	public static final String url = "https://ajax.lianjia.com/map/search/ershoufang/?callback=jQuery1111012389114747347363_1534230881479"
			+ "&city_id=%s"
			+ "&group_type=%s"
			+ "&max_lat=%s"
			+ "&min_lat=%s"
			+ "&max_lng=%s"
			+ "&min_lng=%s"
			+ "&filters=%s"
			+ "&request_ts=%s"
			+ "&source=ljpc"
			+ "&authorization=%s"
			+ "&_=%s";
	private static final HashMap<String, String> headers = new LinkedHashMap<>();

	static {
		headers.put("Host", "ajax.lianjia.com");
		headers.put("Referer", "https://wh.lianjia.com/ditu/");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
	}

	final CommunityMapper communityMapper;
	final RedisTemplate<String, String> redisTemplate;

	public CommunityServiceImpl(CommunityMapper communityMapper, RedisTemplate<String, String> redisTemplate) {
		this.communityMapper = communityMapper;
		this.redisTemplate = redisTemplate;
	}

	public static String getMD5(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		assert md != null;
		md.update(str.getBytes(StandardCharsets.UTF_8));
		byte[] secretBytes = md.digest();
		StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
		for (int i = 0; i < 32 - md5code.length(); i++)
			md5code.insert(0, "0");
		return md5code.toString();
	}

	@Override
	public Community selectByName(String name) {
		return communityMapper.selectByName(name);
	}

	@Override
	public List<Community> selectAll() {
		return communityMapper.selectAll();
	}

	@Override
	public List<Community> selectByDistrict(District district) {
		return communityMapper.selectByDistrict(district);
	}

	@Override
	public int countPreHouseNumByDistrict(District district) {
		return communityMapper.countPreHouseNumByDistrict(district);
	}

	@Override
	public Integer countPreHouseNumByCity(City city) {
		return communityMapper.countPreHouseNumByCity(city);
	}

	@Override
	public List<Community> selectHetMapDataByCity(City city) {
		return communityMapper.selectHetMapDataByCity(city);
	}

	@Override
	public int updateOneLoc(Community community) {
		return communityMapper.updateOneLoc(community);
	}

	public void setLastUpdateTime(District district) {
		SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		redisTemplate.opsForValue().set("lianjiaupdatetime-" + district.getCity_name() + "-" + district.getName(), ymdhms.format(new Date()));
	}

	@Override
	public List<Community> fetchCommunityDataByDistrictFromLianJia(District district) {
		List<Community> communityList = new LinkedList<>();
		try {
			List<BigDecimal> lat = new LinkedList<>();
			List<BigDecimal> lng = new LinkedList<>();
			Optional<String> border = Optional.of(district.getBorder());
			for (String s : border.get().split(";")) {
				lng.add(new BigDecimal(s.split(",")[0]));
				lat.add(new BigDecimal(s.split(",")[1]));
			}
			List<BigDecimal[]> squares = new LinkedList<>();
			BigDecimal step = new BigDecimal("0.02");
			List<BigDecimal> lngOffset = getStepRange(Collections.min(lng), Collections.max(lng), step);
			List<BigDecimal> latOffset = getStepRange(Collections.min(lat), Collections.max(lat), step);
			for (BigDecimal x : lngOffset) {
				for (BigDecimal y : latOffset) {
					BigDecimal[] decimals = new BigDecimal[]{
							y.setScale(6, BigDecimal.ROUND_HALF_EVEN),
							y.subtract(step).setScale(6, RoundingMode.HALF_EVEN),
							x.setScale(6, RoundingMode.HALF_EVEN),
							x.subtract(step).setScale(6, RoundingMode.HALF_EVEN)};
					squares.add(decimals);
				}
			}
			for (int i = 0; i < squares.size(); i++) {
				String time_13 = new Date().getTime() + "";
				BigDecimal[] square = squares.get(i);
				Global.Logger(this).info("正在处理行政区划(" + district.getCity_name() + "_" + district.getName() + ")下小区信息（" + (i + 1) + "/" + squares.size() + ")");
				HashMap<String, String> dict = new LinkedHashMap<>();
				dict.put("group_type", "community");
				dict.put("city_id", district.getCity_id());
				dict.put("max_lat", square[0].toString());
				dict.put("min_lat", square[1].toString());
				dict.put("max_lng", square[2].toString());
				dict.put("min_lng", square[3].toString());
				dict.put("request_ts", time_13);
				String authorization = getNormalAuthorization(dict);
				String realUrl = String.format(url,
						district.getCity_id(), dict.get("group_type"),
						square[0].toString(), square[1].toString(),
						square[2].toString(), square[3].toString(),
						"%7B%7D", time_13,
						authorization, time_13);
				JSONObject res = JSON.parseObject(Global.postHTTPRequest(realUrl, headers, 5));
				communityList.addAll(parseCommunityJsonResult(res, district));
			}
			communityMapper.deleteByDistrict(district);
			communityMapper.bathInsertList(communityList);
			this.setLastUpdateTime(district);
		} catch (Exception e) {
			Global.Logger(this).error(e);
			e.printStackTrace();
		}
		return communityList;
	}

	private List<Community> parseCommunityJsonResult(JSONObject res, District district) {
		List<Community> communityList = new LinkedList<>();
		if (!JSONResultCheck(res))
			return communityList;
		try {
			communityList = res.getJSONObject("data").getJSONArray("list").toJavaList(Community.class);
			Iterator<Community> it = communityList.iterator();
			while (it.hasNext()) {
				Community community = it.next();
				if (null != this.selectByName(community.getName())
						|| !isCommunityInDistrict(community, district)) {
					it.remove();
					continue;
				}
				community.setCity_id(district.getCity_id());
				community.setCity_name(district.getCity_name());
				community.setDistrict_id(district.getId() + "");
				community.setDistrict_name(district.getName());
				getGaoDeLoc(community);
			}
			return communityList;
		} catch (ClassCastException e) {
			Global.Logger(this).error(e);
		}
		Map<String, JSONObject> communityMap = res.getJSONObject("data").getJSONObject("list").toJavaObject(Map.class);
		for (JSONObject communityObj : communityMap.values()) {
			Community community = communityObj.toJavaObject(Community.class);
			if (null != this.selectByName(community.getName())
					|| !isCommunityInDistrict(community, district)) {
				continue;
			}
			community.setCity_id(district.getCity_id());
			community.setCity_name(district.getCity_name());
			community.setDistrict_id(district.getId() + "");
			community.setDistrict_name(district.getName());

			try {
				getGaoDeLoc(community);
				communityList.add(community);
			} catch (Exception e) {
				Global.Logger(this).error(e);
				continue;
			}
		}
		return communityList;
	}

	public void getGaoDeLoc(Community community) {
		try {
			String url = "https://restapi.amap.com/v3/assistant/coordinate/convert?" +
					"locations=" + community.getLongitude() + "," + community.getLatitude() + "&" +
					"coordsys=baidu&" +
					"output=json&" +
					"key=5e842e2d890e0361743c15a6e1ec168a";
			String resStr = Global.doGetHttpRequest(url);
			JSONObject httpres = JSON.parseObject(resStr);
			if (httpres.getIntValue("status") == 1) {
				community.setGaode_lng(httpres.getString("locations").split(",")[0]);
				community.setGaode_lat(httpres.getString("locations").split(",")[1]);
			}
		} catch (Exception e) {
			Global.Logger(this).error(e);
			e.printStackTrace();
		}
	}

	public boolean JSONResultCheck(JSONObject res) {
		if (null == res || res.isEmpty() || res.getIntValue("errno") != 0) {
			if (null != res && !res.isEmpty()) {
				Global.Logger(this).error(res.getString("error"));
			}
			return false;
		}
		return true;
	}

	public boolean isCommunityInDistrict(Community community, District district) {
		BigDecimal pointLat = new BigDecimal(community.getLatitude());
		BigDecimal pointlng = new BigDecimal(community.getLongitude());
		List<BigDecimal> lat = new LinkedList<>();
		List<BigDecimal> lng = new LinkedList<>();
		Optional<String> border = Optional.of(district.getBorder());
		String[] borderPoints = border.get().split(";");
		for (String s : borderPoints) {
			lng.add(new BigDecimal(s.split(",")[0]));
			lat.add(new BigDecimal(s.split(",")[1]));
		}
		BigDecimal maxLat = Collections.max(lat);
		BigDecimal minLat = Collections.min(lat);

		BigDecimal maxLng = Collections.max(lng);
		BigDecimal minLng = Collections.min(lng);

		//初级判断
		boolean isLatValid = pointLat.compareTo(minLat) >= 0 && pointLat.compareTo(maxLat) <= 0;
		boolean isLngValid = pointlng.compareTo(minLng) >= 0 && pointlng.compareTo(maxLng) <= 0;
		if (!isLatValid || !isLngValid) {
			return false;
		}

		//PNPoly算法，也叫射线法，未处理测量点刚好在多边形边上的特殊情况
		boolean crossFlag = false;
		for (int i = 0, j = borderPoints.length - 1; i < borderPoints.length; j = i++) {
			boolean isPointInBetween = (lat.get(i).compareTo(pointLat) > 0) != (lat.get(j).compareTo(pointLat) > 0);
//			CommonUtils.Logger().info(lat.get(j)+"-"+lat.get(i)+"="+lat.get(j).subtract(lat.get(i)));
			if (lat.get(j).compareTo(lat.get(i)) == 0)
				continue;
			boolean isCrossLine = lng.get(j).subtract(lng.get(i))
					.multiply(pointLat.subtract(lat.get(i)))
					.divide(
							lat.get(j).subtract(lat.get(i))
							, 11, RoundingMode.UP).add(lng.get(i)).compareTo(pointlng) > 0;
			if (isPointInBetween && isCrossLine)
				crossFlag = !crossFlag;
		}
		return crossFlag;
	}

	public List<BigDecimal> getStepRange(BigDecimal start, BigDecimal end, BigDecimal step) {
		List<BigDecimal> res = new LinkedList<>();
		do {
			res.add(start);
			start = start.add(step);
		} while (start.compareTo(end) < 0);
		return res;
	}

	public String getNormalAuthorization(HashMap<String, String> dict) {
		String datastr = "vfkpbin1ix2rb88gfjebs0f60cbvhedlcity_id=%sgroup_type=%smax_lat=%s"
				+ "max_lng=%smin_lat=%smin_lng=%srequest_ts=%s";
		datastr = String.format(datastr,
				dict.get("city_id"), dict.get("group_type"), dict.get("max_lat"),
				dict.get("max_lng"), dict.get("min_lat"), dict.get("min_lng"), dict.get("request_ts"));
		return getMD5(datastr);
	}
}
