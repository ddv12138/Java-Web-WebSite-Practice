package ddvudo.web.clients;

import ddvudo.web.bean.City;
import ddvudo.web.bean.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "lianjia")
public interface LianjiaClient {
	@RequestMapping(value = "/city/getCityInfo", method = RequestMethod.GET)
	String getCityInfo(@RequestParam("cityName") String cityName);

	@RequestMapping(value = "/city/available", method = RequestMethod.GET)
	List<City> selectAvailableCity();

	@RequestMapping(value = "/city/selectByName", method = RequestMethod.GET, consumes = "application/json")
	City selectByName(String cityName);

	@PostMapping(value = "/community/list")
	List<Community> getCommunityByCity(@RequestBody String cityName);
}
