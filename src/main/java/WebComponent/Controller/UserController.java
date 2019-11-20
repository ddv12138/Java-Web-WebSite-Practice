package WebComponent.Controller;

import Exceptions.UserAleadyExistsException;
import Exceptions.WrongPasswordException;
import ORM.POJO.User;
import Services.UserService;
import globalUtils.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showUser(Map model) {
		model.put("user", new User());
		return "userreg";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String addUser(User user, RedirectAttributes model) throws UserAleadyExistsException {
		model.addFlashAttribute("user", user);
		userService.saveOne(user);
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(Map model) {
		model.put("user", new User());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, RedirectAttributes model) throws WrongPasswordException {
		String currPassword = user.getPassword();
		user = userService.selectByName(user.getName());
		if (user.getPassword().equals(Global.passwdEncrypt(currPassword))) {
			return "redirect:/";
		} else {
			throw new WrongPasswordException();
		}
	}
}
