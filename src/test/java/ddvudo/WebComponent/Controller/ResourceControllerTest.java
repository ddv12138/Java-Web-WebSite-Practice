package ddvudo.WebComponent.Controller;

import com.alibaba.fastjson.JSON;
import ddvudo.Application;
import ddvudo.ORM.POJO.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
@WithUserDetails("admin")
//这里模拟登陆状态的说明具体参见官方文档
//https://docs.spring.io/spring-security/site/docs/5.0.6.RELEASE/reference/htmlsingle/#test-method-withmockuser
public class ResourceControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build(); //初始化MockMvc对象
	}

	@After
	public void tearDown() {
	}

	@Test
	public void selectResourceList() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/resource?pid=1")
				.accept(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addResource() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/resource")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "菜单管理")
				.param("url", "sa-html/menu/menutree.html")
				.param("pid", "1")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteOne() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.delete("/resource/2")
				.accept(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateOne() throws Exception {
		Resource resource = new Resource();
		resource.setName("what-if-2");
		resource.setId(36);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/resource")
				.accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(resource))
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void selectResourceListByRole() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/resource/byrole/1")
				.accept(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}