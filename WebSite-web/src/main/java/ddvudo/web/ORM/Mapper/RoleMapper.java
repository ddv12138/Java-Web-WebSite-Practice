package ddvudo.web.ORM.Mapper;

import ddvudo.web.ORM.POJO.Role;
import ddvudo.web.ORM.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
	List<Role> listRole();

	Role selectById(Integer id);

	Boolean updateRoleResource(@Param("role") Role role, @Param("resids") List<Integer> resids);

	Integer clearResourceByRole(Role role);

	Boolean insertOne(Role role);

	Boolean deleteOne(Role check);

	Boolean updateOne(Role role);

	List<Role> listRoleByUser(User user);

	Boolean clearRoleByUser(User user);

	Boolean updateRoleByUser(@Param("user") User user, @Param("roleids") List<Integer> roleids);
}
