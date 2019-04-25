package ddvudo.passwdUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class PasswdEncrypt {
	public String passwdEncrypt(String passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		String EncPasswd=base64en.encode(md5.digest(passwd.getBytes("utf-8")));
		return EncPasswd;
	}
	public static PasswdEncrypt getInstance() {
		return new PasswdEncrypt();
	}
}
