package ddvudo.web.Service.Services;

import ddvudo.web.ORM.POJO.Resource;
import ddvudo.web.ORM.POJO.Role;
import ddvudo.web.ORM.POJO.User;

import java.util.List;

public interface ResourceService {
	List<Resource> selectResourceList(Integer pid, User user);

	List<Resource> selectResourceListByRole(Role role);

	int addOne(Resource resource);

	int selectMaxOrder();

	Resource selectById(Integer id);

	int deleteOne(Resource resource);

	int updateOne(Resource resource);
}
