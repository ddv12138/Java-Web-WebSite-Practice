package WebComponent.servlet;

import globalUtils.CommonResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response){
		try {
            String func = request.getParameter("method");
			if(null != func) {
				Method method = this.getClass().getDeclaredMethod(func, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, request, response);
			}
		}catch(NoSuchMethodException e) {
			e.printStackTrace();
			try {
				response.getWriter().println(new CommonResult(false,"未找到服务",null));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (IllegalAccessException|InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
