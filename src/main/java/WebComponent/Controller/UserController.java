package WebComponent.Controller;

import Exceptions.UserAleadyExistsException;
import Exceptions.WrongPasswordException;
import GlobalUtils.Global;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/signup")
	public String showUser(Map model) {
		model.put("user", new User());
		return "userreg";
	}

	@PostMapping("/signup")
	public String addUser(User user, RedirectAttributes model) throws UserAleadyExistsException {
		model.addFlashAttribute("user", user);
		userService.saveOne(user);
		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String showLogin(Map model) {
		model.put("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(User user, RedirectAttributes model) throws WrongPasswordException {
		String currPassword = user.getPassword();
		user = userService.selectByName(user.getName());
		if (user.getPassword().equals(Global.passwdEncrypt(currPassword))) {
			return "redirect:/";
		} else {
			throw new WrongPasswordException();
		}
	}

	@GetMapping("/details")
	@ResponseBody
	public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}
}
