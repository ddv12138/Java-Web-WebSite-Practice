package WebComponent.Controller;

import Exceptions.ResourceNotFoundException;
import Exceptions.UserAleadyExistsException;
import Exceptions.WrongPasswordException;
import GlobalUtils.CommonResult;
import GlobalUtils.Global;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(UserAleadyExistsException.class)
	public CommonResult handleUserAleadyHave(UserAleadyExistsException e) {
		return new CommonResult(false, e.getMessage(), null);
	}

	@ExceptionHandler(WrongPasswordException.class)
	public CommonResult handleWrongPassword(WrongPasswordException e) {
		return new CommonResult(false, e.getMessage(), null);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public CommonResult handleResourceNotFound(ResourceNotFoundException e) {
		return new CommonResult(false, e.getMessage(), null);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public CommonResult handleOtherException(Exception e) {
		Global.Logger(this).error(e);
		return new CommonResult(false, "服务器内部错误", null);
	}
}
