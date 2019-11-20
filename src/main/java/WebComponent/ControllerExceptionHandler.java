package WebComponent;

import Exceptions.UserAleadyExistsException;
import Exceptions.WrongPasswordException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(UserAleadyExistsException.class)
	public String handleUserAleadyHave(Model model) {
		model.addAttribute("message", "用户名已存在！");
		return "message";
	}

	@ExceptionHandler(WrongPasswordException.class)
	public String handleWrongPassword(Model model) {
		model.addAttribute("message", "密码错误");
		return "message";
	}
}
