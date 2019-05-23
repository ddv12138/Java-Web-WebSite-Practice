package WebComponent.Controller;

import globalUtils.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLConnHandler;
import tk.ddvudo.ssrdetection.Utils.URLHandler.URLIOHandler;
import tk.ddvudo.ssrdetection.Utils.dataResolve.DataResolve;
import tk.ddvudo.ssrdetection.beans.Airport;
import tk.ddvudo.ssrdetection.beans.Result;

import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Controller
public class SSRController {
    @RequestMapping("/getSSRDectionResult")
    @ResponseBody
    public CommonResult getSSRDectionResult(@RequestBody Map<String, String> par) {
        CommonResult result = null;
        try {
            String linkurl = par.get("link");
            if (StringUtils.isEmpty(linkurl)) {
                result = new CommonResult(false, "请输入订阅链接", null);
                return result;
            }
            URLConnection con = URLConnHandler.getInstance(linkurl).getConnection();
            Airport data = DataResolve.getInstance().Decode(URLIOHandler.getInstance(URLIOHandler.LinkType.SSR).getResponseContent(con), URLIOHandler.LinkType.SSR);
            List<Result> testRsult = DataResolve.getInstance().serverPingTestMultiThread(1000, data.getServers());
            result = new CommonResult(true, "success", testRsult);
        } catch (Exception e) {
            result = new CommonResult(false, e.getMessage(), null);
            e.printStackTrace();
        }
        return result;
    }
}
