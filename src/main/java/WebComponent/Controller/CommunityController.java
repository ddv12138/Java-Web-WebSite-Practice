package WebComponent.Controller;

import GlobalUtils.CommonResult;
import ORM.POJO.City;
import ORM.POJO.Community;
import WebComponent.Service.Services.CommunityService;
import WebComponent.Service.ServicesImpl.CityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

	CommunityService communityService;

	CityServiceImpl cityService;

	public CommunityController(CommunityService communityService, CityServiceImpl cityService) {
		this.communityService = communityService;
		this.cityService = cityService;
	}

	@RequestMapping("/getCommunitiesByCity")
	@ResponseBody
	public CommonResult getCommunityByCity(String cityName) {
		City city = cityService.selectByName(cityName);
		List<Community> communities = communityService.selectHetMapDataByCity(city);
		return new CommonResult(true, "success", communities);
	}
}
