package WebComponent.Controller;

import ORM.POJO.Role;
import WebComponent.Service.Services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
	RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/list")
	public List<Role> listrole() {
		return roleService.listRole();
	}
}
