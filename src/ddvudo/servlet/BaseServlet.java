package ddvudo.servlet;

import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = -6606927406222858042L;

	@Override
	public void init() throws ServletException {
		
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String func = request.getParameter("func");
			if(func != null) {
				Method method = this.getClass().getDeclaredMethod(func, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, request, response); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		
	}
}
