package WebComponent.servlet;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import ORM.Utils.ResourceUtil;
import globalUtils.CommonResult;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceManage extends BaseServlet {

    public void deleteNode(HttpServletRequest request, HttpServletResponse response) {
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            if (null == request.getParameter("id")) {
                response.getWriter().println(new CommonResult(false, "参数为空", null));
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            if (id == 1 || id == 2) {
                response.getWriter().println(new CommonResult(false, "不允许删除此节点", null));
                return;
            }
            ResourceMapper mapper = session.getMapper(ResourceMapper.class);
            ResourceTable res = mapper.selectByID(id);
            if (null == res) {
                response.getWriter().println(new CommonResult(false, "节点不存在", null));
                return;
            }
            ResourceUtil.getInstance().deleteResource(res, session);
            session.commit();
            response.getWriter().println(new CommonResult(true, "节点删除成功", null));
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
    }
}
