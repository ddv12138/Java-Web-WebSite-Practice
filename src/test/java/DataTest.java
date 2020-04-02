import GlobalUtils.Application;
import GlobalUtils.Global;
import ORM.POJO.City;
import ORM.POJO.Community;
import WebComponent.Service.Services.CityService;
import WebComponent.Service.Services.CommunityService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataTest {
	@Autowired
	CityService cityService;
	@Autowired
	CommunityService communityService;

	@Test
	@Ignore
	public void ncovTest() throws IOException {
		Global.downLoadFromUrl("https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.json", "Wuhan-2019-nCoV.json", ".\\ncovData\\");
	}

	@Test
	@Ignore
	public void insertGaoDeLngAngLat() {
		String citystr = "厦门";
		City city = cityService.selectByName(citystr);
		List<Community> communities = communityService.selectHetMapDataByCity(city);
		for (Community community : communities) {
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
				int i = communityService.updateOneLoc(community);
				Global.Logger(this).info(communities.size() + "/" + communities.indexOf(community));
			}
		}
	}
}
