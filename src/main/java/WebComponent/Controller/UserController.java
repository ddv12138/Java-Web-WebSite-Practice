package WebComponent.Controller;

import Exceptions.UserAleadyExistsException;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	@PreAuthorize("hasAuthority('管理员')")
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
		ArrayList<Integer> list = new ArrayList<>(1);
		list.add(maxid);
		data.forEach(user -> {
			if (user.getId() > list.get(0)) {
				list.add(0, user.getId());
			}
		});
		res.put("data", data);
		res.put("maxid", list.get(0));
		res.put("count", userService.selectCount());
		return res;
	}
}
