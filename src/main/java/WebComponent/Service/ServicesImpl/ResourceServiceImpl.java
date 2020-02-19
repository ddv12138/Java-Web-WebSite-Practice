package WebComponent.Service.ServicesImpl;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.Resource;
import ORM.POJO.Role;
import ORM.POJO.User;
import WebComponent.Service.Services.ResourceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Predicate;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {
	ResourceMapper resourceMapper;

	public ResourceServiceImpl(ResourceMapper resourceMapper) {
		this.resourceMapper = resourceMapper;
	}

	@Override
	@Cacheable(value = "Resource", key = "'selectResourceList-'+#pid+'-'+#user.name")
	public List<Resource> selectResourceList(Integer pid, User user) {
		boolean isAdmin = user.getAuthorities().parallelStream().anyMatch((Predicate<GrantedAuthority>) grantedAuthority -> grantedAuthority.getAuthority().equals("管理员"));
		return isAdmin ? resourceMapper.selectAllResourceList(pid) : resourceMapper.selectResourceList(pid, user.getId());
	}

	@Override
	public List<Resource> selectResourceListByRole(Role role) {
		return resourceMapper.selectResourceListByRole(role);
	}

	@Override
	@CacheEvict(value = "Resource", allEntries = true)
	public int addOne(Resource resource) {
		if (null == resource.getOrder()) {
			resource.setOrder(resourceMapper.selectMaxOrder() + 1);
		}
		Resource parent = resourceMapper.selectById(resource.getPid());
		Assert.notNull(parent, "父节点不存在");
		resource.setLevel(parent.getLevel() + 1);
		String plevel = resourceMapper.selectMaxLastLevel(parent);
		char[] plevelarr = plevel.toCharArray();
		int low = plevelarr[parent.getLevel() * 2 + 1] - '0';
		if (low < 9) {
			low += 1;
			plevelarr[parent.getLevel() * 2 + 1] = (char) (low + '0');
		} else {
			int high = plevelarr[parent.getLevel() * 2] - '0';
			if (high < 9) {
				high += 1;
				plevelarr[parent.getLevel() * 2] = (char) (high + '0');
			} else {
				throw new RuntimeException("菜单层级溢出");
			}
		}
		plevel = new String(plevelarr);
		resource.setLevelId(plevel);
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
	@CacheEvict(value = "Resource", allEntries = true)
	public int deleteOne(Resource resource) {
		return resourceMapper.deleteOne(resource);
	}

	@Override
	@CacheEvict(value = "Resource", allEntries = true)
	public int updateOne(Resource resource) {
		return resourceMapper.updateOne(resource);
	}
}
