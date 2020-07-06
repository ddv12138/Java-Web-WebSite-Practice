package ddvudo.WebComponent.Controller;

import ddvudo.GlobalUtils.CommonResult;
import ddvudo.GlobalUtils.Global;
import ddvudo.GlobalUtils.SystemCode;
import ddvudo.ORM.POJO.City;
import ddvudo.WebComponent.Service.Services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
@Api(value = "城市信息", tags = "城市信息")
class CityController {
	CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping("/getCityInfo")
	@ResponseBody
	@ApiOperation(value = "单个城市详情", notes = "获取单个城市的详细信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cityName", value = "城市名称", example = "武汉", required = true)
	})
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
