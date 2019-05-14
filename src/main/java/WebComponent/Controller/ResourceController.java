package WebComponent.Controller;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import globalUtils.CommonResult;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class ResourceController {
    @RequestMapping("/getTabList")
    @ResponseBody
    public CommonResult getTabList(@RequestBody(required = false) Map par) throws IOException {
        System.out.println(par);
        return new CommonResult(true, "", null);
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
