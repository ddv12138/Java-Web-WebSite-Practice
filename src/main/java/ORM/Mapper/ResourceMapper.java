package ORM.Mapper;

import ORM.POJO.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
	List<Resource> selectResourceList(@Param("pid") Integer pid);
}
