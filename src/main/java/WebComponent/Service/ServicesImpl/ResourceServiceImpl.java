package WebComponent.Service.ServicesImpl;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.Resource;
import WebComponent.Service.Services.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {
	@javax.annotation.Resource
	ResourceMapper resourceMapper;

	public List<Resource> selectResourceList(Integer pid) {
		return resourceMapper.selectResourceList(pid);
	}
}
