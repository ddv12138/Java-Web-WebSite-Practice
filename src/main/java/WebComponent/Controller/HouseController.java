package WebComponent.Controller;

import ORM.POJO.House;
import Services.HouseService;
import globalUtils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HouseController {
	@Resource
	HouseService service;

	@RequestMapping("/getHouseByCityName")
	public CommonResult getHouseByCityName(@RequestParam String city_name) {
		List<House> res = service.selectHouseByCityName(city_name);
		return new CommonResult(true, "success", res);
	}

}
