package ORM.Mapper;

import ORM.POJO.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
	List<Role> listRole();
}
