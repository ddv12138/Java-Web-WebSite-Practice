package WebComponent.Controller;

import ORM.POJO.City;
import ORM.POJO.Community;
import Services.CityService;
import Services.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {
	@RequestMapping("/")
	public String helloView() {
		return "index";
	}

	@Resource
	CommunityService communityService;

	@Resource
	CityService cityService;

	@RequestMapping("/index")
	public List<Community> getCommunityByCity(String cityName) {
		City city = cityService.selectByName(cityName);
		return communityService.selectHetMapDataByCity(city);
	}
}
