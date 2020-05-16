package ddvudo.WebComponent.Controller;

import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import ddvudo.WebComponent.Service.CityServiceImpl;
import ddvudo.WebComponent.Service.Services.CommunityService;
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

	@RequestMapping("/list")
	@ResponseBody
	public List<Community> getCommunityByCity(String cityName) {
		City city = cityService.selectByName(cityName);
		List<Community> communities = communityService.selectHetMapDataByCity(city);
		return communities;
	}
}
