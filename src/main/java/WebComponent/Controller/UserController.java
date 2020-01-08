package WebComponent.Controller;

import Exceptions.UserAleadyExistsException;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

	@DeleteMapping
	public Boolean deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}

	@GetMapping
	public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('管理员')")
	public Map<String, Object> selectList(@RequestParam(defaultValue = "-1") Integer maxid, @RequestParam("pagesize") int limit) {
		LinkedHashMap<String, Object> res = new LinkedHashMap<>();
		List<User> data = userService.selectList(maxid, limit);
		final Optional<Integer>[] maxidop = new Optional[]{Optional.of(maxid)};
		data.forEach(user -> {
			if (user.getId() > maxidop[0].get()) {
				maxidop[0] = Optional.of(user.getId());
			}
		});
		res.put("data", data);
		res.put("maxid", maxidop[0].get());
		res.put("count", userService.selectCount());
		return res;
	}
}
