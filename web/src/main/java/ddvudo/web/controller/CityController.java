package ddvudo.web.controller;

import ddvudo.web.bean.Community;
import ddvudo.web.clients.LianjiaClient;
import ddvudo.web.utils.CommonResult;
import ddvudo.web.bean.City;
import ddvudo.web.utils.Global;
import ddvudo.web.utils.SystemCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@Api(value = "城市信息", tags = "城市信息")
class CityController {

	LianjiaClient lianjiaClient;

	public CityController(LianjiaClient lianjiaClient) {
		this.lianjiaClient = lianjiaClient;
	}

	@GetMapping("/getCityInfo")
	@ApiOperation(value = "单个城市详情", notes = "获取单个城市的详细信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cityName", value = "城市名称", example = "武汉", required = true)
	})
	public CommonResult getCityInfo(String cityName) {
		return new CommonResult(SystemCode.OK, "success", lianjiaClient.getCityInfo(cityName));
	}

	@GetMapping("/available")
	@ApiOperation(value = "城市列表", notes = "获取可用城市列表", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<City> selectAvailableCity() {
		return lianjiaClient.selectAvailableCity();
	}
}
