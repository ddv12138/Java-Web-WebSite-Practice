package ddvudo.WebComponent.Controller;

import ddvudo.GlobalUtils.CommonResult;
import ddvudo.GlobalUtils.Global;
import ddvudo.GlobalUtils.SystemCode;
import ddvudo.ORM.POJO.City;
import ddvudo.WebComponent.Service.Services.CityService;
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
	public CommonResult getCityInfo(String cityName) {
		String res = Global.doGetHttpRequest("http://api.map.baidu.com/place/v2/search?query=" + cityName + "&region=全国&output=json&ak=pB1cQmp3mKHrI8PMYQGoogGvGnpahqNn");
		return new CommonResult(SystemCode.OK, "success", res);
	}

	@GetMapping("/avaliable")
	@ResponseBody
	public List<City> selectAvaliableCity() {
		return cityService.selectAvaliableCity();
	}
}
