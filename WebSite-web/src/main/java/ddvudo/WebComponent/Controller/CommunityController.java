package ddvudo.WebComponent.Controller;

import ddvudo.ORM.POJO.Community;
import ddvudo.WebComponent.Service.Services.LianjiaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

	LianjiaService lianjiaService;

	public CommunityController(LianjiaService lianjiaService) {
		this.lianjiaService = lianjiaService;
	}

	@GetMapping("/list")
	@ResponseBody
	public List<Community> getCommunityByCity(String cityName) {
		List<Community> communities = lianjiaService.getCommunityByCity(cityName);
		return communities;
	}
}
