package ddvudo.WebComponent.Service.Services;


import ddvudo.GlobalUtils.CommonResult;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("lianjia")
public interface LianjiaService {

	@RequestMapping(value = "/city/getCityInfo", method = RequestMethod.GET)
	CommonResult getCityInfo(@RequestParam String cityName);

	@RequestMapping(value = "/city/available", method = RequestMethod.GET)
	List<City> selectAvailableCity();

	@RequestMapping(value = "/city/selectByName", method = RequestMethod.GET)
	City selectByName(@RequestParam String cityName);

	@RequestMapping(value = "/community/list", method = RequestMethod.POST)
	List<Community> getCommunityByCity(String cityName);
}
