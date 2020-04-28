import ddvudo.Application;
import ddvudo.WebComponent.Service.Services.CityService;
import ddvudo.WebComponent.Service.Services.CommunityService;
import ddvudo.WebComponent.Service.Services.DistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataTest {
	@Autowired
	CityService cityService;
	@Autowired
	CommunityService communityService;
	@Autowired
	DistrictService districtService;

	@Test
	public void insertGaoDeLngAngLat() {
		return;
//		String citystr = "武汉";
//		City city = cityService.selectByName(citystr);
//		List<District> districtList = districtService.selectByCity(city);
////		Global.Logger(this).info(communityService.fetchCommunityDataByDistrictFromLianJia(districtList.get(0)));
//		List<Community> communities = communityService.selectHetMapDataByCity(city);
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
//				Global.Logger(this).info(communities.size() + "/" + communities.indexOf(community));
//			}
//		}
	}
}
