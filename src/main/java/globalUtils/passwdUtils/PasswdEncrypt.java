package globalUtils.passwdUtils;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswdEncrypt {
	public static String passwdEncrypt(String passwd) throws NoSuchAlgorithmException {
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		return base64en.encode(md5.digest(passwd.getBytes(StandardCharsets.UTF_8)));
	}
	private PasswdEncrypt(){}
}
