package WebComponent.Controller;

import Exceptions.ResourceNotFoundException;
import ORM.POJO.Resource;
import WebComponent.Service.Services.ResourceService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	ResourceService resourceService;

	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@GetMapping
	public List<Resource> selectResourceList(Integer pid) {
		if (null == pid)
			pid = 0;
		return resourceService.selectResourceList(pid);
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
	public int updateOne(Resource menu) {
		Assert.notNull(menu.getId(), "进行更新操作时，主键字段不允许为null");
		int check;
		if ((check = resourceService.updateOne(menu)) > 0) {
			return check;
		} else {
			throw new RuntimeException("更新失败");
		}
	}
}
