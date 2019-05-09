package WebComponent.servlet;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import ORM.Utils.ResourceUtil;
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
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            Integer pnodeid = null;
            if (null != request.getParameter("pnodeid")) {
                pnodeid = Integer.parseInt(request.getParameter("pnodeid"));
            }
            if (null == pnodeid) return;
            String name = request.getParameter("name");
            String cnname = request.getParameter("cnname");
            String urlpath = request.getParameter("urlpath");
            Integer istop = null;
            if (null != request.getParameter("istop")) {
                istop = Integer.parseInt(request.getParameter("istop"));
            }
            Boolean haschild = null;
            if (null != request.getParameter("haschild")) {
                haschild = "1".equals(request.getParameter("haschild"));
            }
            ResourceMapper mapper = session.getMapper(ResourceMapper.class);
            ResourceTable pnode = mapper.selectByID(pnodeid);
            ResourceTable subnode = ResourceUtil.getInstance().getNewSubNode(pnode, name, cnname, istop, null, urlpath, haschild);
            int i = mapper.insertByParent(pnode, subnode);
            if (i >= 0) {
                session.commit();
                response.getWriter().println(new CommonResult(true, "插入成功", subnode.toString()));
            } else {
                response.getWriter().println(new CommonResult(false, "插入失败", subnode.toString()));
                throw new RuntimeException("节点插入失败");
            }
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteNode(HttpServletRequest request, HttpServletResponse response) {
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            if (null == request.getParameter("id")) {
                response.getWriter().println(new CommonResult(false, "参数为空", null));
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
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
