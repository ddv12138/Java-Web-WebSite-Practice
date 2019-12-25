package WebComponent.Service.ServicesImpl;

import ORM.Mapper.RoleMapper;
import ORM.POJO.Role;
import WebComponent.Service.Services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	RoleMapper mapper;

	public RoleServiceImpl(RoleMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Role> listRole() {
		return mapper.listRole();
	}

	@Override
	public Role selectById(Integer id) {
		return mapper.selectById(id);
	}

	@Override
	public Boolean updateRoleResource(Integer roleid, List<Integer> resids) {
		Role role = mapper.selectById(roleid);
		Assert.notNull(role, "角色不存在");
		mapper.clearResourceByRole(role);
		return mapper.updateRoleResource(role, resids);
	}

	@Override
	public Boolean insertOne(Role role) {
		Assert.notNull(role.getName(), "角色名称不能为空");
		return mapper.insertOne(role);
	}
}
