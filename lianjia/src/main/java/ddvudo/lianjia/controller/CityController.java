package ddvudo.lianjia.controller;

import ddvudo.lianjia.bean.City;
import ddvudo.lianjia.service.CityService;
import ddvudo.lianjia.utils.Global;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
class CityController {
	CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@RequestMapping(value = "/getCityInfo", method = RequestMethod.GET)
	public String getCityInfo(String cityName) {
		return Global.doGetHttpRequest("http://api.map.baidu.com/place/v2/search?query=" + cityName + "&region=全国&output=json&ak=pB1cQmp3mKHrI8PMYQGoogGvGnpahqNn");
	}

	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public List<City> selectAvaliableCity() {
		return cityService.selectAvaliableCity();
	}

	@RequestMapping(value = "/selectByName", method = RequestMethod.GET)
	public City selectByName(String city_name) {
		return cityService.selectByName(city_name);
	}
}
