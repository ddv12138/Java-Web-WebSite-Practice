package WebComponent.Controller;

import GlobalUtils.CommonResult;
import GlobalUtils.Global;
import GlobalUtils.SystemCode;
import ORM.POJO.City;
import WebComponent.Service.Services.CityService;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("/getCityInfo")
	@ResponseBody
	public CommonResult getCityInfo(String cityName) {
		String res = Global.doGetHttpRequest("http://api.map.baidu.com/place/v2/search?query=" + cityName + "&region=全国&output=json&ak=pB1cQmp3mKHrI8PMYQGoogGvGnpahqNn");
		return new CommonResult(SystemCode.OK, "success", res);
	}

	@RequestMapping("/avaliable")
	@ResponseBody
	public List<City> selectAvaliableCity() {
		return cityService.selectAvaliableCity();
	}
}
