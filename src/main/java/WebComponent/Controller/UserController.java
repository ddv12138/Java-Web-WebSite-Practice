package WebComponent.Controller;

import Exceptions.UserAleadyExistsException;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public int addUser(User user) throws UserAleadyExistsException {
		return userService.saveOne(user);
	}

	@GetMapping
	public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}

	@GetMapping("/{limit}")
	@PreAuthorize("hasAuthority('管理员')")
	public List<User> selectList(@RequestParam(defaultValue = "-1") int maxid, @PathVariable int limit) {
		return userService.selectList(maxid, limit);
	}
}
