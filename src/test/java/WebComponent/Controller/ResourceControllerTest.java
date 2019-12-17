package WebComponent.Controller;

import GlobalUtils.Application;
import ORM.POJO.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional
public class ResourceControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	private MockHttpSession session;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
		session = new MockHttpSession();
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
		session.setAttribute("user", user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
	}

	@After
	public void tearDown() {
	}

	@Test
	public void selectResourceList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/resource?pid=1")
				.accept(MediaType.APPLICATION_JSON)
				.session(session)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addResource() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/resource")
				.accept(MediaType.APPLICATION_JSON)
				.session(session)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "菜单管理")
				.param("url", "sa-html/menu/menutree.html")
				.param("pid", "1")
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteOne() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/resource/2")
				.accept(MediaType.APPLICATION_JSON)
				.session(session)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}