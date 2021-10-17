package ddvudo.web.Controller;

import ddvudo.web.ORM.POJO.Community;
import ddvudo.web.Service.Services.LianjiaService;
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

	LianjiaService lianjiaService;

	public CommunityController(LianjiaService lianjiaService) {
		this.lianjiaService = lianjiaService;
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取社区信息", notes = "通过城市获取社区信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cityName", value = "城市名称", example = "武汉", required = true)
	})
	public List<Community> getCommunityByCity(String cityName) {
		List<Community> communities = lianjiaService.getCommunityByCity(cityName);
		return communities;
	}
}
