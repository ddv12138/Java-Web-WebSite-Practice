package WebComponent.Controller;

import WebComponent.Service.Services.HouseService;
import org.springframework.stereotype.Controller;

@Controller
public class HouseController {
	HouseService service;

	public HouseController(HouseService service) {
		this.service = service;
	}
}
