import ORM.POJO.City;
import ORM.POJO.Community;
import Services.CityService;
import Services.CommunityService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import globalUtils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DataTest {
	@Autowired
	CityService cityService;
	@Autowired
	CommunityService communityService;

	@Test
	public void insertGaoDeLngAngLat() {
		String citystr = "深圳";
		City city = cityService.selectByName(citystr);
		List<Community> communities = communityService.selectHetMapDataByCity(city);
		for (Community community : communities) {
			String url = "https://restapi.amap.com/v3/assistant/coordinate/convert?" +
					"locations=" + community.getLongitude() + "," + community.getLatitude() + "&" +
					"coordsys=baidu&" +
					"output=json&" +
					"key=5e842e2d890e0361743c15a6e1ec168a";
			String resStr = CommonUtils.doGetHttpRequest(url);
			JSONObject res = JSON.parseObject(resStr);
			if (res.getIntValue("status") == 1) {
				community.setGaode_lng(res.getString("locations").split(",")[0]);
				community.setGaode_lat(res.getString("locations").split(",")[1]);
				int i = communityService.updateOneLoc(community);
				CommonUtils.Logger().info(community.getUuid() + "_" + community.getName() + "_" + i);
			}
		}
	}
}
