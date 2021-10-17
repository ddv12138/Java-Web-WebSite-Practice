package ddvudo.lianjia.WebComponent.Controller;

import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.ORM.POJO.Community;
import ddvudo.lianjia.WebComponent.Service.CityServiceImpl;
import ddvudo.lianjia.WebComponent.Service.Services.CommunityService;
import ddvudo.web.utils.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping(value = "/list")
	public List<Community> getCommunityByCity(@RequestBody String cityName) {
		Global.Logger(this).info(cityName.replaceAll("\"", ""));
		City city = cityService.selectByName(cityName.replaceAll("\"", ""));
		return communityService.selectHetMapDataByCity(city);
	}
}
