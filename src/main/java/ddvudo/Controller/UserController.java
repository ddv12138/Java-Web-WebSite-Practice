package ddvudo.Controller;

import ddvudo.Exceptions.UserAleadyExistsException;
import ddvudo.ORM.Mapper.UserMapper;
import ddvudo.ORM.POJO.User;
import ddvudo.Service.Services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	UserService userService;
	UserMapper mapper;

	public UserController(UserService userService, UserMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
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
	public Map<String, Object> selectList(@RequestParam(defaultValue = "-1", name = "pagenum") Integer pageNum, @RequestParam("pagesize") int pageSize) {
		LinkedHashMap<String, Object> res = new LinkedHashMap<>();
		res.put("data", userService.selectList(pageNum, pageSize));
		res.put("count", userService.selectCount());
		return res;
	}

	@PutMapping("ban")
	public Boolean banUser(@RequestBody User user) {
		return userService.userBan(user, true);
	}

	@PutMapping("unban")
	public Boolean unBanUser(@RequestBody User user) {
		return userService.userBan(user, false);
	}
}
