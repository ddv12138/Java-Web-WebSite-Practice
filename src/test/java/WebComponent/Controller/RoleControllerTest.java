package WebComponent.Controller;

import GlobalUtils.Application;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class RoleControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;

	@Before
	public void setUp() {
		//初始化MockMvc对象
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void listrole() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateresource() throws Exception {
		JSONObject object = new JSONObject();
		object.put("roleid", "1");
		Integer[] resids = new Integer[]{1, 2, 3, 4, 5, 6};
		object.put("resids", resids);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/role/updateresource")
				.content(object.toJSONString())
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void insertNewOne() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.param("name", "test")
				.param("desc", "testdesc")
				.param("lock", "1")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteOne() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.param("id", "9")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateOne() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.param("id", "9")
				.param("name", "test")
				.param("desc", "testdesc")
				.param("lock", "1")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}