package WebComponent.Service.Services;

import ORM.POJO.Role;

import java.util.List;

public interface RoleService {
	List<Role> listRole();

	Role selectById(Integer id);

	Boolean updateRoleResource(Integer roleid, List<Integer> resids);

	Boolean insertOne(Role role);

	Boolean deleteOne(Role role);

	Boolean updateOne(Role role);
}
