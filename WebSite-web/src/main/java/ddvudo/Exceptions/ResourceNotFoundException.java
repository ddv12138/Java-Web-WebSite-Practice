package ddvudo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "节点未找到")
public class ResourceNotFoundException extends Throwable {
	/**
	 *
	 */
	private static final long serialVersionUID = -495568795113913555L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
