package ddvudo.web.Controller;

import ddvudo.web.GlobalUtils.CommonResult;
import ddvudo.web.ORM.POJO.City;
import ddvudo.web.Service.Services.LianjiaService;
import ddvudo.root.GlobalUtils.Global;
import ddvudo.root.GlobalUtils.SystemCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
@Api(value = "城市信息", tags = "城市信息")
class CityController {
	LianjiaService lianjiaService;

	public CityController(LianjiaService lianjiaService) {
		this.lianjiaService = lianjiaService;
	}

	@GetMapping("/getCityInfo")
	@ApiOperation(value = "单个城市详情", notes = "获取单个城市的详细信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cityName", value = "城市名称", example = "武汉", required = true)
	})
	public CommonResult getCityInfo(String cityName) {
		String res = Global.doGetHttpRequest("http://api.map.baidu.com/place/v2/search?query=" + cityName + "&region=全国&output=json&ak=pB1cQmp3mKHrI8PMYQGoogGvGnpahqNn");
		return new CommonResult(SystemCode.OK, "success", res);
	}

	@GetMapping("/available")
	@ApiOperation(value = "城市列表", notes = "获取可用城市列表", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<City> selectAvailableCity() {
		return lianjiaService.selectAvailableCity();
	}
}
