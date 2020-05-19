package ddvudo.WebComponent.Service.Services;


import ddvudo.GlobalUtils.CommonResult;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("lianjia")
public interface LianjiaService {

	@GetMapping("/city/getCityInfo")
	CommonResult getCityInfo(String cityName);

	@GetMapping("/city/avaliable")
	List<City> selectAvaliableCity();

	@GetMapping("/city/selectByName")
	City selectByName(String cityName);

	@GetMapping("/community/list")
	List<Community> getCommunityByCity(String cityName);
}
