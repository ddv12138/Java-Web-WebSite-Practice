package ddvudo.lianjia.WebComponent.Controller;

import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.WebComponent.Service.Services.CityService;
import ddvudo.root.GlobalUtils.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
class CityController {
	CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping("/getCityInfo")
	@ResponseBody
	public String getCityInfo(String cityName) {
		return Global.doGetHttpRequest("http://api.map.baidu.com/place/v2/search?query=" + cityName + "&region=全国&output=json&ak=pB1cQmp3mKHrI8PMYQGoogGvGnpahqNn");
	}

	@GetMapping("/avaliable")
	@ResponseBody
	public List<City> selectAvaliableCity() {
		return cityService.selectAvaliableCity();
	}

	@GetMapping("/selectByName")
	@ResponseBody
	public City selectByName(String city_name) {
		return cityService.selectByName(city_name);
	}
}
