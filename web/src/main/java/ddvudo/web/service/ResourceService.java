package ddvudo.web.service;

import ddvudo.web.bean.Resource;
import ddvudo.web.bean.Role;
import ddvudo.web.bean.User;

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
