package ddvudo.web.GlobalUtils.SecurityHandlers;

import com.alibaba.fastjson.JSON;
import ddvudo.web.GlobalUtils.CommonResult;
import ddvudo.root.GlobalUtils.Global;
import ddvudo.root.GlobalUtils.SystemCode;

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
			response.getWriter().flush();
		} catch (IOException e) {
			Global.Logger(RestUtil.class).error(e.getMessage(), e);
		}
	}
}
