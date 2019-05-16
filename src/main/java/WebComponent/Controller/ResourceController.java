package WebComponent.Controller;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import ORM.Utils.ResourceUtil;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import globalUtils.CommonResult;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

@Controller
public class ResourceController {
    @RequestMapping("/getTabList")
    @ResponseBody
    public CommonResult getTabList(@RequestBody LinkedHashMap<String, Object> par) {
        String pid = String.valueOf(par.get("parentid"));
        if (null == par.get("parentid")) {
            pid = null;
        }
        Boolean ismanage = (Boolean) par.get("ismanage");
        if (null == par.get("ismanage")) ismanage = false;
        CommonResult resp;
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            if ((StringUtils.isNullOrEmpty(pid)) && !ismanage) {
                ResourceTable rt = new ResourceTable();
                rt.setIstop(1);
                ResourceTable[] res = rm.selectByExample(rt);
                resp = new CommonResult(true, "sucess", JSON.toJSONString(res));
            } else if ((StringUtils.isNullOrEmpty(pid)) && ismanage) {
                ResourceTable res = rm.selectByID(1);
                resp = new CommonResult(true, "sucess", JSON.toJSONString(res));
            } else {
                ResourceTable pnode = rm.selectByID(Integer.parseInt(pid));
                ResourceTable[] res = rm.selectNextLevelNode(pnode);
                resp = new CommonResult(true, "sucess", JSON.toJSONString(res));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            resp = new CommonResult(false, e.getMessage(), null);
        } finally {
            session.close();
        }
        return resp;
    }

    @RequestMapping("/updateResNodeInfo")
    @ResponseBody
    public CommonResult updateResNodeInfo(@RequestBody ResourceTable res) {
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            ResourceMapper mapper = session.getMapper(ResourceMapper.class);
            int num = mapper.updateByExample(res);
            if (num >= 0) {
                session.commit();
                return new CommonResult(true, "更新成功", null);
            }
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return new CommonResult(false, "更新失败", null);
    }

    @RequestMapping("/insertNodeByParent")
    @ResponseBody
    public CommonResult insertNodeByParent(@RequestBody LinkedHashMap<String, String> par) {
        CommonResult result = null;
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            Integer pnodeid = null;
            if (null != par.get("pnodeid")) {
                pnodeid = Integer.parseInt(par.get("pnodeid"));
            }
            if (null == pnodeid) return new CommonResult(false, "插入失败", null);
            String name = par.get("name");
            String cnname = par.get("cnname");
            String urlpath = par.get("urlpath");
            Integer istop = null;
            if (null != par.get("istop")) {
                istop = Integer.parseInt(par.get("istop"));
            }
            Boolean haschild = null;
            if (null != par.get("haschild")) {
                haschild = "1".equals(par.get("haschild"));
            }
            ResourceMapper mapper = session.getMapper(ResourceMapper.class);
            ResourceTable pnode = mapper.selectByID(pnodeid);
            ResourceTable subnode = ResourceUtil.getInstance().getNewSubNode(pnode, name, cnname, istop, null, urlpath, haschild);
            int i = mapper.insertByParent(pnode, subnode);
            if (i >= 0) {
                session.commit();
                result = new CommonResult(true, "插入成功", subnode.toString());
            } else {
                result = new CommonResult(false, "插入失败", subnode.toString());
                throw new RuntimeException("节点插入失败");
            }
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @RequestMapping("/deleteNode")
    @ResponseBody
    public CommonResult deleteNode(@RequestBody LinkedHashMap<String, String> par) {
        SqlSession session = DataBaseManage.getSqlSessionFactory().openSession();
        try {
            if (null == par.get("id")) {
                return new CommonResult(false, "参数为空", null);
            }
            int id = Integer.parseInt(par.get("id"));
            if (id == 1 || id == 2) {
                return new CommonResult(false, "不允许删除此节点", null);
            }
            ResourceMapper mapper = session.getMapper(ResourceMapper.class);
            ResourceTable res = mapper.selectByID(id);
            if (null == res) {
                return new CommonResult(false, "节点不存在", null);
            }
            ResourceUtil.getInstance().deleteResource(res, session);
            session.commit();
            return new CommonResult(true, "节点删除成功", null);
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return new CommonResult(false, "参数为空", null);
    }
}
