package ddvudo.lianjia.WebComponent.Controller;

import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.WebComponent.Service.Services.CityService;
import ddvudo.root.GlobalUtils.Global;
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
