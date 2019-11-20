package WebComponent.Controller;

import ORM.POJO.User;
import Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String User(Map model) {
		model.put("user", new User());
		return "userinfo";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addUser(User user, Model model) {
		userService.saveOne(user);
		return "userinfo";
	}
}
