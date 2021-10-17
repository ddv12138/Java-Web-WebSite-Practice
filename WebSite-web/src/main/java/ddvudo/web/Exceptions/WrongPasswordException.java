package ddvudo.web.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "密码错误")
public class WrongPasswordException extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = -6465965723608011763L;
}
