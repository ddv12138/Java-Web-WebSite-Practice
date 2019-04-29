package WebComponent.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceManage extends BaseServlet {
    public void getTabList(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("parentid");
    }
}
