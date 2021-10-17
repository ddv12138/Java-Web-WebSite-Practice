package ddvudo.web.controller;

import ddvudo.web.exception.ControllerException;
import ddvudo.web.service.ExceptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
	ExceptionService exceptionService;

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
}
