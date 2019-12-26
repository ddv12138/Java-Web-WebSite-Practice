package WebComponent.Controller;

import ORM.POJO.Role;
import WebComponent.Service.Services.RoleService;
import com.alibaba.fastjson.JSON;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
	RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public List<Role> listrole() {
		return roleService.listRole();
	}

	@PutMapping("/updateresource")
	public Boolean updateresource(@RequestBody Map<String, String> par) {
		Integer roleId = Integer.parseInt(par.get("roleid"));
		List<Integer> resIds = JSON.parseArray(par.get("resids"), Integer.class);
		return roleService.updateRoleResource(roleId, resIds);
	}

	@PostMapping
	public Boolean insertNewOne(Role role) {
		return roleService.insertOne(role);
	}

	@DeleteMapping
	public Boolean deleteOne(@RequestBody Role role) {
		return roleService.deleteOne(role);
	}

	@PutMapping
	public Boolean updateOne(@RequestBody Role role) {
		Assert.notNull(role.getId(), "角色主键更新时不允许为空");
		return roleService.updateOne(role);
	}
}
