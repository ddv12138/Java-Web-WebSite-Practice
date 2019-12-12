import GlobalUtils.Application;
import ORM.POJO.Resource;
import ORM.POJO.User;
import com.alibaba.fastjson.JSON;
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
public class ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	private MockHttpSession session;

	@Before
	public void setupMockMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
		session = new MockHttpSession();
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
		session.setAttribute("user", user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
	}

	@Test
	public void selectResourceList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/resource?pid=-1")
				.accept(MediaType.APPLICATION_JSON)
				.session(session)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteResource() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/resource/8")
				.accept(MediaType.APPLICATION_JSON)
				.session(session)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addResource() throws Exception {
		Resource resource = new Resource();
		resource.setName("test");
		resource.setUrl("url");
		resource.setPid(5);
		mvc.perform(MockMvcRequestBuilders.post("/resource")
				.accept(MediaType.APPLICATION_JSON)
				.session(session).content(JSON.toJSONString(resource))
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
