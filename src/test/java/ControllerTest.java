import WebComponent.Controller.BaseController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ControllerTest {
	@Test
	public void baseControllerTest() throws Exception {
		BaseController baseController = new BaseController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(baseController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("index"));
	}
}
