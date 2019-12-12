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
	ResourceMapper resourceMapper;

	public ResourceServiceImpl(ResourceMapper resourceMapper) {
		this.resourceMapper = resourceMapper;
	}

	@Override
	public List<Resource> selectResourceList(Integer pid) {
		return resourceMapper.selectResourceList(pid);
	}

	@Override
	public int addOne(Resource resource) {
		if (null == resource.getOrder()) {
			resource.setOrder(resourceMapper.selectMaxOrder() + 1);
		}
		Resource parent = resourceMapper.selectById(resource.getPid());
		resource.setLevel(parent.getLevel() + 1);
		resource.setLevelid(parent.getLevelid() + resource.getLevel());
		return resourceMapper.addOne(resource);
	}

	@Override
	public int selectMaxOrder() {
		return resourceMapper.selectMaxOrder();
	}

	@Override
	public Resource selectById(Integer id) {
		return resourceMapper.selectById(id);
	}

	@Override
	public int deleteOne(Resource resource) {
		return resourceMapper.deleteOne(resource);
	}
}
