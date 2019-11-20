package Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "用户已经存在！")
public class UserAleadyExistsException extends Throwable {

	public UserAleadyExistsException(String message) {
		super(message);
	}
}
