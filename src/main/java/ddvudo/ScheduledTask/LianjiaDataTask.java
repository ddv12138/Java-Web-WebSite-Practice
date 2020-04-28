package ddvudo.ScheduledTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ddvudo.GlobalUtils.Global;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import ddvudo.ORM.POJO.District;
import ddvudo.WebComponent.Service.Services.CityService;
import ddvudo.WebComponent.Service.Services.CommunityService;
import ddvudo.WebComponent.Service.Services.DistrictService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("server")
public class LianjiaDataTask {
	final CommunityService communityService;
	final DistrictService districtService;
	final CityService cityService;
	final RedisTemplate<String, String> redisTemplate;
	final String[] cities = {"深圳", "武汉"};

	public LianjiaDataTask(CommunityService communityService, CityService cityService, DistrictService districtService, RedisTemplate<String, String> redisTemplate) {
		this.communityService = communityService;
		this.cityService = cityService;
		this.districtService = districtService;
		this.redisTemplate = redisTemplate;
	}


	@Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 180 * 1000)
	public void getCommunityDataByCity() {
		for (String citystr : cities) {
			City city = cityService.selectByName(citystr);
			List<District> districtList = districtService.selectByCity(city);
			for (District district : districtList) {
				List<Community> communities = communityService.fetchCommunityDataByDistrictFromLianJia(district);
				for (Community community : communities) {
					redisTemplate.opsForList().leftPush("community", community.getName() + "__" + LocalDate.now().toString());
					String url = "https://restapi.amap.com/v3/assistant/coordinate/convert?" +
							"locations=" + community.getLongitude() + "," + community.getLatitude() + "&" +
							"coordsys=baidu&" +
							"output=json&" +
							"key=5e842e2d890e0361743c15a6e1ec168a";
					String resStr = Global.doGetHttpRequest(url);
					JSONObject res = JSON.parseObject(resStr);
					if (res.getIntValue("status") == 1) {
						community.setGaode_lng(res.getString("locations").split(",")[0]);
						community.setGaode_lat(res.getString("locations").split(",")[1]);
						communityService.updateOneLoc(community);
						Global.Logger(this).info(communities.size() + "/" + communities.indexOf(community));
					}
				}
			}
		}
	}
}
