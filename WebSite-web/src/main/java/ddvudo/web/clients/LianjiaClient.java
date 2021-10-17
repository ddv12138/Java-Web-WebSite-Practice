package ddvudo.web.clients;

import ddvudo.web.bean.City;
import ddvudo.web.bean.Community;
import ddvudo.web.utils.Global;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "lianjia")
public interface LianjiaClient {
	@RequestMapping(value = "/city/getCityInfo", method = RequestMethod.GET)
	public String getCityInfo(String cityName);

	@RequestMapping(value = "/city/available", method = RequestMethod.GET)
	List<City> selectAvailableCity();

	@RequestMapping(value = "/city/selectByName", method = RequestMethod.GET, consumes = "application/json")
	City selectByName(String cityName);

	@PostMapping(value = "/community/list")
	List<Community> getCommunityByCity(@RequestBody String cityName);
}
