package ddvudo.web.controller;

import ddvudo.web.bean.Community;
import ddvudo.web.clients.LianjiaClient;
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
@Api(value = "社区信息", tags = "社区信息")
@RequestMapping("/community")
public class CommunityController {

	LianjiaClient lianjiaClient;

	public CommunityController(LianjiaClient lianjiaClient) {
		this.lianjiaClient = lianjiaClient;
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取社区信息", notes = "通过城市获取社区信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cityName", value = "城市名称", example = "武汉", required = true)
	})
	public List<Community> getCommunityByCity(String cityName) {
		return lianjiaClient.getCommunityByCity(cityName);
	}
}
