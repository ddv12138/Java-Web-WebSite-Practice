package WebComponent.Controller;

import Exceptions.ResourceNotFoundException;
import ORM.POJO.Resource;
import ORM.POJO.Role;
import ORM.POJO.User;
import WebComponent.Service.Services.ResourceService;
import WebComponent.Service.Services.RoleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	ResourceService resourceService;
	RoleService roleService;

	public ResourceController(ResourceService resourceService, RoleService roleService) {
		this.resourceService = resourceService;
		this.roleService = roleService;
	}

	@GetMapping
	public List<Resource> selectResourceList(Integer pid, @AuthenticationPrincipal User user) {
		if (null == pid)
			pid = 0;
		return resourceService.selectResourceList(pid, user);
	}

	@GetMapping("/byrole/{roleid}")
	public List<Resource> selectResourceListByRole(@PathVariable Integer roleid) {
		Role role = roleService.selectById(roleid);
		Assert.notNull(role, "该角色不存在");
		return resourceService.selectResourceListByRole(role);
	}

	@PostMapping
	public Resource addResource(Resource menu) {
		menu.setId(resourceService.addOne(menu));
		return menu;
	}

	@DeleteMapping("/{id}")
	public Resource deleteOne(@PathVariable @NotNull int id) throws ResourceNotFoundException {
		Resource resource = resourceService.selectById(id);
		if (resourceService.deleteOne(resource) > 0) {
			return resource;
		} else {
			throw new ResourceNotFoundException("节点不存在");
		}
	}

	@PutMapping
	public int updateOne(@RequestBody Resource menu) {
		Assert.notNull(menu.getId(), "进行更新操作时，主键字段不允许为null");
		int check;
		if ((check = resourceService.updateOne(menu)) > 0) {
			return check;
		} else {
			throw new RuntimeException("更新失败");
		}
	}
}
