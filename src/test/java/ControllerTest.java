import WebComponent.Controller.SpittrController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ControllerTest {
	@Test
	public void baseControllerTest() throws Exception {
		SpittrController spittrController = new SpittrController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittrController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("spittrviews/index"));
	}
}
