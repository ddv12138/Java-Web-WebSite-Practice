import ddvudo.Application;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataTest {
//	@Autowired
//	CityService cityService;
//	@Autowired
//	CommunityService communityService;
//	@Autowired
//	DistrictService districtService;

	@Test
	public void insertGaoDeLngAngLat() {
		return;
//		String citystr = "深圳";
//		City city = cityService.selectByName(citystr);
//		List<District> districtList = districtService.selectByCity(city);
//////		Global.Logger(this).info(communityService.fetchCommunityDataByDistrictFromLianJia(districtList.get(0)));
//		List<Community> communities = communityService.selectHetMapDataByCity(city);
//		int c=0;
//		for (Community community : communities) {
//			String url = "https://restapi.amap.com/v3/assistant/coordinate/convert?" +
//					"locations=" + community.getLongitude() + "," + community.getLatitude() + "&" +
//					"coordsys=baidu&" +
//					"output=json&" +
//					"key=5e842e2d890e0361743c15a6e1ec168a";
//			String resStr = Global.doGetHttpRequest(url);
//			JSONObject res = JSON.parseObject(resStr);
//			if (res.getIntValue("status") == 1) {
//				community.setGaode_lng(res.getString("locations").split(",")[0]);
//				community.setGaode_lat(res.getString("locations").split(",")[1]);
//				int i = communityService.updateOneLoc(community);
//				Global.Logger(this).info(c+++"/"+communities.size());
//			}
//		}
	}

	@Autowired
	StringEncryptor encryptor;

	@Test
	public void getPass() {
		String url = encryptor.encrypt(
				"jdbc:postgresql://194.156.133.226:5432/how2jdb?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8");
		String name = encryptor.encrypt("postgres");
		String password = encryptor.encrypt("liukang951006");
		System.out.println("----------------");
		System.out.println(url);
		System.out.println(name);
		System.out.println(password);
		System.out.println(encryptor.encrypt("194.156.133.226"));
		System.out.println(encryptor.encrypt("6379"));
		System.out.println(encryptor.encrypt(
				"jdbc:postgresql://localhost:5432/how2jdb?characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8"));
		Assert.assertTrue(name.length() > 0);
		Assert.assertTrue(password.length() > 0);
	}
}
