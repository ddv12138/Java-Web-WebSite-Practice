package ddvudo.web.Controller;

import ddvudo.web.ORM.POJO.Ncov;
import ddvudo.web.Service.Services.NcovService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "疫情数据", tags = "疫情数据")
@RequestMapping("/ncov")
public class NcovController {
	NcovService ncovService;

	public NcovController(NcovService ncovService) {
		this.ncovService = ncovService;
	}

	@GetMapping("/world")
	@ApiOperation(value = "世界疫情数据", notes = "获取给定日世界疫情数据", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "datestr", value = "日期字符串（yyyy-MM-dd）", example = "2020-07-06", required = true)
	})
	public List<Ncov> getWorldNcovDataByDate(String datestr) throws ParseException {
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		if (null == datestr) {
			return ncovService.getWorldNcovDataByDate(new Date());
		}
		return ncovService.getWorldNcovDataByDate(ymd.parse(datestr));
	}

	@GetMapping("/updatetime")
	@ApiOperation(value = "最后一次更新时间", notes = "获取世界疫情数据最后一次更新时间", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Long getLastUpdateTime() throws ParseException {
		return ncovService.getLastUpdateTime().getTime();
	}
}
