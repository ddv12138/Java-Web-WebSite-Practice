import GlobalUtils.Global;
import org.junit.Test;

import java.util.regex.Pattern;

public class ControllerTest {
	@Test
	public void baseControllerTest() throws Exception {
//		SpittrController spittrController = new SpittrController();
//		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittrController).build();
//		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("spittrviews/index"));
	}

	@Test
	public void regxTest() {
		String content = "/layui/css/modules/laydate/default/laydate.css?v=5.0.9\\";
		String content2 = "/layui/css/modules/laydate/default/laydate.css";
		String content3 = "/layui/css/modules/laydate/default/laydate.js";
		String content4 = "/layui/css/modules/laydate/default/laydate.ico";
		String content5 = "/layui/css/modules/laydate/default/laydate.png";
		String content6 = "/layui/css/modules/laydate/default/laydate.zip";
		String pattern = ".*(css|js|ico|png)\\??[^/\\\\]*";
		Global.Logger().info(Pattern.matches(pattern, content));
		Global.Logger().info(Pattern.matches(pattern, content2));
		Global.Logger().info(Pattern.matches(pattern, content3));
		Global.Logger().info(Pattern.matches(pattern, content4));
		Global.Logger().info(Pattern.matches(pattern, content5));
		Global.Logger().info(Pattern.matches(pattern, content6));
	}
}
