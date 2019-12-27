package WebComponent.Controller;

import GlobalUtils.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional
@WithMockUser(username = "测试用户", authorities = {"管理员", "普通用户"})
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
	}

	@Test
	public void getCurrentUser() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/user")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk()).
				andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addUser() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/user")
				.param("username", "ddv")
				.param("password", "123")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk()).
				andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void selectList() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/user/10")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk()).
				andDo(MockMvcResultHandlers.print());
	}
}