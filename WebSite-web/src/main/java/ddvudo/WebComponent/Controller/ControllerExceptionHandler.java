package ddvudo.WebComponent.Controller;

import ddvudo.Exceptions.ResourceNotFoundException;
import ddvudo.Exceptions.UserAleadyExistsException;
import ddvudo.Exceptions.WrongPasswordException;
import ddvudo.GlobalUtils.CommonResult;
import ddvudo.root.GlobalUtils.SystemCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(UserAleadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResult handleUserAleadyHave(UserAleadyExistsException e) {
		return new CommonResult(SystemCode.InnerError, e.getMessage(), null);
	}

	@ExceptionHandler(WrongPasswordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResult handleWrongPassword(WrongPasswordException e) {
		return new CommonResult(SystemCode.InnerError, e.getMessage(), null);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CommonResult handleResourceNotFound(ResourceNotFoundException e) {
		return new CommonResult(SystemCode.InnerError, e.getMessage(), null);
	}

//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public CommonResult handleOtherException(Exception e) {
//		Global.Logger(this).error(e);
//		return new CommonResult(SystemCode.InnerError, e.getMessage(), null);
//	}
}
