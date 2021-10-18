package ddvudo.lianjia.controller;

import ddvudo.lianjia.bean.City;
import ddvudo.lianjia.bean.Community;
import ddvudo.lianjia.service.impl.CityServiceImpl;
import ddvudo.lianjia.service.CommunityService;
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
		City city = cityService.selectByName(cityName.replaceAll("\"", ""));
		return communityService.selectHetMapDataByCity(city);
	}
}
