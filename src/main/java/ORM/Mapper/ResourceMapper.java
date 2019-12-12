package ORM.Mapper;

import ORM.POJO.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceMapper {
	List<Resource> selectResourceList(@Param("pid") Integer pid);

	int addOne(Resource resource);

	int selectMaxOrder();

	int deleteOne(Resource resource);

	Resource selectById(int id);
}
