package WebComponent.Controller;

import Services.EnterpriseService;
import globalUtils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class EnterpriseController {
    @Resource
    EnterpriseService service;

    @RequestMapping("/getEnterpriseList")
    @ResponseBody
    public CommonResult listEnterprise(@RequestBody LinkedHashMap pars) {
        int page = Integer.parseInt(Optional.ofNullable(String.valueOf(pars.get("page"))).orElse("1"));
        int limit = Integer.parseInt(Optional.ofNullable(String.valueOf(pars.get("limit"))).orElse("10"));
        int offset = (page - 1) * limit;
        List data = service.listEnterprise(offset, limit);
        long count = service.countEnterprise();
        HashMap res = new LinkedHashMap();
        res.put("count", count);
        res.put("data", data);
        return new CommonResult(true, "sucess", res);
    }
}
