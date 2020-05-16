package ddvudo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "节点未找到")
public class ResourceNotFoundException extends Throwable {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
