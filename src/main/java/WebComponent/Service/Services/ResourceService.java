package WebComponent.Service.Services;

import ORM.POJO.Resource;

import java.util.List;

public interface ResourceService {
	List<Resource> selectResourceList(Integer pid);

	int addOne(Resource resource);

	int selectMaxOrder();

	Resource selectById(Integer id);

	int deleteOne(Resource resource);

	int updateOne(Resource resource);
}
