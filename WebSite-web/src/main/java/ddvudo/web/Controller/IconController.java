package ddvudo.web.Controller;

import ddvudo.web.Service.Services.IconService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/icon")
public class IconController {
	IconService iconService;

	public IconController(IconService service) {
		this.iconService = service;
	}

	@GetMapping("/list")
	public Map<String, Object> selectList(@RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum, @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
		return iconService.selectList(pagenum, pagesize);
	}
}
