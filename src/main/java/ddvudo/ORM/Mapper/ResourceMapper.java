package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.Resource;
import ddvudo.ORM.POJO.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {
	List<Resource> selectResourceList(@Param("pid") Integer pid, @Param("userid") Integer userid);

	List<Resource> selectAllResourceList(@Param("pid") Integer pid);

	List<Resource> selectResourceListByRole(@Param("role") Role role);

	int addOne(Resource resource);

	int selectMaxOrder();

	int deleteOne(Resource resource);

	Resource selectById(int id);

	String selectMaxLastLevel(Resource resource);

	int updateOne(Resource resource);

}
