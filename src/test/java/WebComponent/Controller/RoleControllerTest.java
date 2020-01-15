package WebComponent.Controller;

import GlobalUtils.Application;
import ORM.POJO.Role;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class RoleControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;


	@Before
	public void setUp() throws Exception {
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
				.content(object.toJSONString()).contentType(MediaType.APPLICATION_JSON)
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
				.contentType(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteOne() throws Exception {
		Role role = new Role();
		role.setId(1);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.delete("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(JSON.toJSONString(role)).contentType(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andDo(MockMvcResultHandlers.print());

		role.setId(75);
		actions = mvc.perform(MockMvcRequestBuilders.delete("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(JSON.toJSONString(role))
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateOne() throws Exception {
		Role role = new Role();
		role.setId(9);
		role.setName("test");
		role.setDesc("testdesc");
		role.setLock(true);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/role")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(JSON.toJSONString(role)).contentType(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void listRoleByUser() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/role/byuser")
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.param("userid", "2").contentType(MediaType.APPLICATION_JSON)
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateRoleByUser() throws Exception {
		JSONObject object = new JSONObject();
		object.put("userid", "2");
		Integer[] roleids = new Integer[]{1, 2, 3, 4, 5, 6};
		object.put("roleids", roleids);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/role/byuser")
				.content(object.toJSONString())
				.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
		);
		actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		actions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}