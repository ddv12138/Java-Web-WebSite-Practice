package WebComponent.Controller;

import ORM.POJO.City;
import ORM.POJO.Community;
import Services.CityService;
import Services.CommunityService;
import com.alibaba.fastjson.JSON;
import globalUtils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommunityController {

	@Resource
	CommunityService communityService;

	@Resource
	CityService cityService;

	@RequestMapping("/getCommunitiesByCity")
	@ResponseBody
	public CommonResult getCommunityByCity(String cityName) {
		City city = cityService.selectByName(cityName);
		List<Community> communities = communityService.selectHetMapDataByCity(city);
		return new CommonResult(true, "success", JSON.toJSONString(communities));
	}
}
