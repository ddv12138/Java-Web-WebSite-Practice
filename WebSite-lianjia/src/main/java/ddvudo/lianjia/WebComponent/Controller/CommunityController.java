package ddvudo.lianjia.WebComponent.Controller;

import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.ORM.POJO.Community;
import ddvudo.lianjia.WebComponent.Service.CityServiceImpl;
import ddvudo.lianjia.WebComponent.Service.Services.CommunityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

	CommunityService communityService;

	CityServiceImpl cityService;

	public CommunityController(CommunityService communityService, CityServiceImpl cityService) {
		this.communityService = communityService;
		this.cityService = cityService;
	}

	@PostMapping("/list")
	@ResponseBody
	public List<Community> getCommunityByCity(String cityName) {
		City city = cityService.selectByName(cityName);
		List<Community> communities = communityService.selectHetMapDataByCity(city);
		return communities;
	}
}
