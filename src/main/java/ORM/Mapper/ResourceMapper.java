package ORM.Mapper;

import ORM.POJO.Resource;
import ORM.POJO.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceMapper {
	List<Resource> selectResourceList(@Param("pid") Integer pid);

	List<Resource> selectResourceListByRole(@Param("role") Role role);

	int addOne(Resource resource);

	int selectMaxOrder();

	int deleteOne(Resource resource);

	Resource selectById(int id);

	String selectMaxLastLevel(Resource resource);

	int updateOne(Resource resource);

}
