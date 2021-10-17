package ddvudo.web.controller;

import ddvudo.web.service.EnterpriseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {
	EnterpriseService service;

	public EnterpriseController(EnterpriseService service) {
		this.service = service;
	}

	@RequestMapping("/getEnterpriseList")
	@ResponseBody
	public HashMap<String, String> listEnterprise(@RequestBody HashMap<String, String> pars) {
		return pars;
	}
}
