package WebComponent.servlet;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import com.alibaba.fastjson.JSON;
import globalUtils.CommonResult;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceManage extends BaseServlet {
    public void getTabList(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("parentid");
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            if (null == pid) {
                ResourceTable rt = new ResourceTable();
                rt.setIstop(0);
                ResourceTable[] res = rm.selectByExample(rt);
                response.getWriter().println(new CommonResult(true, "sucess", JSON.toJSONString(res)));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().println(new CommonResult(false, e.getMessage(), null));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            response.getWriter().println(new CommonResult(false, "fail", null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
