package WebComponent.Controller;

import globalUtils.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HelloController {
    @RequestMapping("/test")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/getTabList")
    @ResponseBody
    public CommonResult getTabList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new CommonResult(true, "", null);
    }
}
