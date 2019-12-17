package WebComponent.Controller;

import Exceptions.ResourceNotFoundException;
import ORM.POJO.Resource;
import WebComponent.Service.Services.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	ResourceService resourceService;

	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@GetMapping
	@ResponseBody
	public List<Resource> selectResourceList(Integer pid) {
		if (null == pid)
			pid = 0;
		return resourceService.selectResourceList(pid);
	}

	@PostMapping
	@ResponseBody
	public Resource addResource(Resource menu) {
		menu.setId(resourceService.addOne(menu));
		return menu;
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public Resource deleteOne(@PathVariable @NotNull int id) throws ResourceNotFoundException {
		Resource resource = resourceService.selectById(id);
		if (resourceService.deleteOne(resource) > 0) {
			return resource;
		} else {
			throw new ResourceNotFoundException("节点不存在");
		}
	}
}
