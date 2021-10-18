package ddvudo.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "用户已经存在！")
public class UserAleadyExistsException extends Throwable {

	/**
	 *
	 */
	private static final long serialVersionUID = 785566007935411945L;

	public UserAleadyExistsException(String message) {
		super(message);
	}
}
