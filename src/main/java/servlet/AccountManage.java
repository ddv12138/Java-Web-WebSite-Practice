package servlet;

import globalUtils.UUIDManage;
import passwdUtils.PasswdEncrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AccountManage extends BaseServlet {
	private static final long serialVersionUID = 4293957245032244232L;

	public void loginInValidate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String passwd = request.getParameter("passwd");
			String EncPasswd = PasswdEncrypt.passwdEncrypt(passwd);
			if(username.equals("admin") && EncPasswd.equals("ISMvKXpXpadDiUoOSoAfww==")) {
				String html = "<div style='color:green'>success</div>";
				PrintWriter pw = response.getWriter();
				pw.println(html);
				request.setCharacterEncoding("UTF-8");
				response.setStatus(200);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void signupValidate(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("tel");
		String id = UUIDManage.getUUIDString();
		System.out.println(username);
	}
}
