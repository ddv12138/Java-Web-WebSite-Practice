package ddvudo.servlet;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ddvudo.passwdUtils.PasswdEncrypt;

public class AccountManage extends BaseServlet {
	private static final long serialVersionUID = 4293957245032244232L;

	public void loginInValidate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String passwd = request.getParameter("passwd");
			String EncPasswd = PasswdEncrypt.getInstance().passwdEncrypt(passwd);
			request.setCharacterEncoding("UTF-8");
			response.setStatus(200);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
