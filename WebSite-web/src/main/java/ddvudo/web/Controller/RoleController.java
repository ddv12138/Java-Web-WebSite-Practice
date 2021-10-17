package ddvudo.web.Controller;

import com.alibaba.fastjson.JSON;
import ddvudo.web.ORM.POJO.Role;
import ddvudo.web.Service.Services.RoleService;
import io.swagger.annotations.Api;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
@Api(value = "系统角色", tags = "系统角色")
public class RoleController {
	RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public List<Role> listrole() {
		return roleService.listRole();
	}

	@GetMapping("/byuser")
	public List<Role> listRoleByUser(@RequestParam Integer userid) {
		return roleService.listRoleByUser(userid);
	}

	@PutMapping("/byuser")
	public Boolean updateRoleByUser(@RequestBody Map<String, String> par) {
		Integer userid = Integer.parseInt(par.get("userid"));
		List<Integer> roleids = JSON.parseArray(par.get("roleids"), Integer.class);
		return roleService.updateRoleByUser(userid, roleids);
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
