package ddvudo.WebComponent.Service.Services;


import ddvudo.GlobalUtils.CommonResult;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("lianjia")
@Component
public interface LianjiaService {

	@GetMapping("/city/getCityInfo")
	CommonResult getCityInfo(String cityName);

	@GetMapping("/city/avaliable")
	List<City> selectAvaliableCity();

	@GetMapping("/city/selectByName")
	City selectByName(String cityName);

	@PostMapping("/community/list")
	List<Community> getCommunityByCity(@RequestParam String cityName);
}
