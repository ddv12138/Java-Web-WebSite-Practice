package WebComponent.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

	@Override
	public void init() {
		
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
            String func = request.getParameter("method");
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
