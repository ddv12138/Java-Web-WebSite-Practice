package ddvudo.WebComponent.Service.Services;


import ddvudo.GlobalUtils.CommonResult;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("lianjia")
public interface LianjiaService {

	@RequestMapping(value = "/city/getCityInfo", method = RequestMethod.GET, consumes = "application/json")
	CommonResult getCityInfo(String cityName);

	@RequestMapping(value = "/city/available", method = RequestMethod.GET)
	List<City> selectAvailableCity();

	@RequestMapping(value = "/city/selectByName", method = RequestMethod.GET, consumes = "application/json")
	City selectByName(String cityName);

	@RequestMapping(value = "/community/list", method = RequestMethod.POST, consumes = "application/json")
	List<Community> getCommunityByCity(String cityName);
}
