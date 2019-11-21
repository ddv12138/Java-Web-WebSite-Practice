package WebComponent.Controller;

import GlobalUtils.CommonResult;
import ORM.POJO.ResourceTable;
import Services.ResourceService;
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
		Boolean isManage = (Boolean) par.get("ismanage");
		if (null == par.get("ismanage")) isManage = false;
		return service.getTabList(pid, isManage);
    }

    @RequestMapping("/updateResNodeInfo")
    @ResponseBody
    public CommonResult updateResNodeInfo(@RequestBody ResourceTable res) {
//        int num = service.updateResNodeInfo(res);
//        if (num >= 0) {
//            return new CommonResult(true, "更新成功", null);
//        } else {
            return new CommonResult(false, "更新失败", null);
//        }
    }

    @RequestMapping("/insertNodeByParent")
    @ResponseBody
    public CommonResult insertNodeByParent(@RequestBody LinkedHashMap<String, String> par) {
        CommonResult result = null;
//        int subnode = service.insertNodeByParent(par.get("pnodeid"), par.get("name"), par.get("cnname"), par.get("istop"), null, par.get("urlpath"), par.get("haschild"));
//        if (subnode > 0) {
//            result = new CommonResult(true, "插入成功", subnode);
//        } else {
//            result = new CommonResult(false, "插入失败", subnode);
//        }
        return result;
    }

    @RequestMapping("/deleteNode")
    @ResponseBody
    public CommonResult deleteNode(@RequestBody LinkedHashMap<String, String> par) {
//        if (service.deleteResource(par.get("id")) > 0) {
//            return new CommonResult(true, "节点删除成功", null);
//        } else {
            return new CommonResult(false, "操作错误", null);
//        }
    }
}
