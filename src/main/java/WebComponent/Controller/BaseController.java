package WebComponent.Controller;

import Services.SpittrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class BaseController {
	@RequestMapping("/")
	public String helloView() {
		return "index";
	}

	@Autowired
	SpittrService spittrService;

	@RequestMapping("/latest")
	public String getLatestSpittrs(@RequestParam(value = "count", defaultValue = "50") int count, Model model) {
		model.addAttribute("spittrList", spittrService.selectLatest(count));
		return "latest";
	}
}
