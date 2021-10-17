package ddvudo.web.utils;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswdEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence charSequence) {
		return Global.passwdEncrypt(charSequence.toString());
	}

	@Override
	public boolean matches(CharSequence raw, String encoded) {
		return Global.passwdEncrypt(raw.toString()).equals(encoded);
	}
}
