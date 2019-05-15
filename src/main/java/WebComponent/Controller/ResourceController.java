package WebComponent.Controller;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
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
}
