package WebComponent.Service.Services;

import ORM.POJO.Resource;
import ORM.POJO.Role;

import java.util.List;

public interface ResourceService {
	List<Resource> selectResourceList(Integer pid);

	List<Resource> selectResourceListByRole(Role role);

	int addOne(Resource resource);

	int selectMaxOrder();

	Resource selectById(Integer id);

	int deleteOne(Resource resource);

	int updateOne(Resource resource);
}
