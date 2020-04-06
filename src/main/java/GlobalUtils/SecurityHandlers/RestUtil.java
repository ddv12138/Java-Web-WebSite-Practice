package GlobalUtils.SecurityHandlers;

import GlobalUtils.CommonResult;
import GlobalUtils.Global;
import GlobalUtils.SystemCode;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestUtil {
	public static void response(HttpServletResponse response, SystemCode systemCode) {
		response(response, systemCode, systemCode.getMessage());
	}

	public static void response(HttpServletResponse response, SystemCode systemCode, String msg) {
		response(response, systemCode, msg, null);
	}


	public static void response(HttpServletResponse response, SystemCode systemCode, String msg, Object content) {
		try {
			CommonResult res = new CommonResult<>(systemCode, msg, content);
			String resStr = JSON.toJSONString(res);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(resStr);
		} catch (IOException e) {
			Global.Logger(RestUtil.class).error(e.getMessage(), e);
		}
	}
}
