package WebComponent.Controller;

import ORM.POJO.Resource;
import WebComponent.Service.Services.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	@javax.annotation.Resource
	ResourceService resourceService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Resource> selectResourceList(Integer pid) {
		if (null == pid)
			pid = -1;
		return resourceService.selectResourceList(pid);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public boolean selectResourceList(Resource menu) {
		if (null == menu.getOrder()) {
			menu.setOrder(resourceService.selectMaxOrder() + 1);
		}
		return resourceService.addOne(menu) > 0;
	}
}
