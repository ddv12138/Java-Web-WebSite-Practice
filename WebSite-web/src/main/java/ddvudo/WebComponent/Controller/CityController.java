package ddvudo.WebComponent.Controller;

import ddvudo.GlobalUtils.CommonResult;
import ddvudo.ORM.POJO.City;
import ddvudo.WebComponent.Service.Services.LianjiaService;
import ddvudo.root.GlobalUtils.Global;
import ddvudo.root.GlobalUtils.SystemCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
class CityController {
	LianjiaService lianjiaService;

	public CityController(LianjiaService lianjiaService) {
		this.lianjiaService = lianjiaService;
	}

	@GetMapping("/getCityInfo")
	@ResponseBody
	public CommonResult getCityInfo(String cityName) {
		String res = Global.doGetHttpRequest("http://api.map.baidu.com/place/v2/search?query=" + cityName + "&region=全国&output=json&ak=pB1cQmp3mKHrI8PMYQGoogGvGnpahqNn");
		return new CommonResult(SystemCode.OK, "success", res);
	}

	@GetMapping("/available")
	@ResponseBody
	public List<City> selectAvailableCity() {
		return lianjiaService.selectAvailableCity();
	}
}
