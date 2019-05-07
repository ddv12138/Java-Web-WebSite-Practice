package WebComponent.servlet;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import globalUtils.CommonResult;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceManage extends BaseServlet {
    public void getTabList(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("parentid");
        boolean ismanage = null != request.getParameter("ismanage");
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            if ((StringUtils.isNullOrEmpty(pid.trim())) && !ismanage) {
                ResourceTable rt = new ResourceTable();
                rt.setIstop(1);
                ResourceTable[] res = rm.selectByExample(rt);
                response.getWriter().println(new CommonResult(true, "sucess", JSON.toJSONString(res)));
                return;
            } else if ((StringUtils.isNullOrEmpty(pid.trim())) && ismanage) {
                ResourceTable res = rm.selectByID(1);
                response.getWriter().println(new CommonResult(true, "sucess", JSON.toJSONString(res)));
                return;
            } else {
                ResourceTable pnode = rm.selectByID(Integer.parseInt(pid));
                ResourceTable[] res = rm.selectNextLevelNode(pnode);
                response.getWriter().println(new CommonResult(true, "sucess", JSON.toJSONString(res)));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            try {
                response.getWriter().println(new CommonResult(false, e.getMessage(), null));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            session.close();
        }
        try {
            response.getWriter().println(new CommonResult(false, "fail", null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertNodeByParent(HttpServletRequest request, HttpServletResponse response) {

    }
}
