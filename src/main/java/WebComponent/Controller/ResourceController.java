package WebComponent.Controller;

import ORM.POJO.ResourceTable;
import Services.ResourceService;
import globalUtils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

@Controller
public class ResourceController {
    @Resource
    ResourceService service;

    @RequestMapping("/getTabList")
    @ResponseBody
    public CommonResult getTabList(@RequestBody LinkedHashMap<String, Object> par) {
        String pid = String.valueOf(par.get("parentid"));
        if (null == par.get("parentid")) {
            pid = null;
        }
        Boolean ismanage = (Boolean) par.get("ismanage");
        if (null == par.get("ismanage")) ismanage = false;
        return service.getTabList(pid, ismanage);
    }

    @RequestMapping("/updateResNodeInfo")
    @ResponseBody
    public CommonResult updateResNodeInfo(@RequestBody ResourceTable res) {
        int num = service.updateResNodeInfo(res);
        if (num >= 0) {
            return new CommonResult(true, "更新成功", null);
        } else {
            return new CommonResult(false, "更新失败", null);
        }
    }

    @RequestMapping("/insertNodeByParent")
    @ResponseBody
    public CommonResult insertNodeByParent(@RequestBody LinkedHashMap<String, String> par) {
        CommonResult result = null;
        Integer pnodeid = null;
        if (null != par.get("pnodeid")) {
            pnodeid = Integer.parseInt(par.get("pnodeid"));
        }
        if (null == pnodeid) return new CommonResult(false, "插入失败", null);
        Integer istop = null;
        if (null != par.get("istop")) {
            istop = Integer.parseInt(par.get("istop"));
        }
        Boolean haschild = null;
        if (null != par.get("haschild")) {
            haschild = "1".equals(par.get("haschild"));
        }
        int subnode = service.insertNodeByParent(pnodeid, par.get("name"), par.get("cnname"), istop, null, par.get("urlpath"), haschild);
        if (subnode > 0) {
            result = new CommonResult(true, "插入成功", subnode);
        } else {
            result = new CommonResult(false, "插入失败", subnode);
        }
        return result;
    }

    @RequestMapping("/deleteNode")
    @ResponseBody
    public CommonResult deleteNode(@RequestBody LinkedHashMap<String, String> par) {
        if (service.deleteResource(par.get("id")) > 0) {
            return new CommonResult(true, "节点删除成功", null);
        } else {
            return new CommonResult(false, "操作错误", null);
        }
    }
}
