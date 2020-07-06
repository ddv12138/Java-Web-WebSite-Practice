package ddvudo.WebComponent.Controller;

import ddvudo.ORM.POJO.Ncov;
import ddvudo.WebComponent.Service.Services.NcovService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api("疫情数据")
@RequestMapping("/ncov")
public class NcovController {
	NcovService ncovService;

	public NcovController(NcovService ncovService) {
		this.ncovService = ncovService;
	}

	@GetMapping("/world")
	public List<Ncov> getWorldNcovDataByDate(String datestr) throws ParseException {
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		if (null == datestr) {
			return ncovService.getWorldNcovDataByDate(new Date());
		}
		return ncovService.getWorldNcovDataByDate(ymd.parse(datestr));
	}

	@GetMapping("/updatetime")
	public Long getLastUpdateTime() throws ParseException {
		return ncovService.getLastUpdateTime().getTime();
	}
}
