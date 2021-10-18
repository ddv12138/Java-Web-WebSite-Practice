package ddvudo.web.controller;

import com.netflix.discovery.converters.Auto;
import ddvudo.web.clients.LianjiaClient;
import ddvudo.web.exception.ControllerException;
import ddvudo.web.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
	ExceptionService exceptionService;
	@Autowired
	LianjiaClient lianjiaClient;

	public ExceptionController(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}

	@GetMapping("/controller")
	public Boolean getControllerException() {
		throw new ControllerException("来自controller的异常");
	}

	@GetMapping("/service")
	public Boolean getServiceException() {
		return exceptionService.getServiceException();
	}

	@GetMapping("/mapper")
	public Boolean getMapperException() {
		return exceptionService.getMapperException();
	}

	@GetMapping("/test")
	public String test(){
		return lianjiaClient.getCityInfo("武汉");
	}
}
