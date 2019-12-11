package ORM.Mapper;

import ORM.POJO.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {
	List<Resource> selectResourceList(@Param("pid") Integer pid);

	int addOne(Resource resource);

	int selectMaxOrder();
}
