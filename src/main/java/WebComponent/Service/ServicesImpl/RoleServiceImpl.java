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
		Role check = mapper.selectById(role.getId());
		Assert.state(null == check, "角色已存在");
		return mapper.insertOne(role);
	}

	@Override
	public Boolean deleteOne(Role role) {
		Assert.notNull(role.getId(), "角色id不存在");
		Role check = mapper.selectById(role.getId());
		Assert.notNull(check, "角色不存在");
		Assert.state(!check.getLock(), "此角色是维持系统正常运行的重要角色，已被锁定，不可删除");
		return mapper.deleteOne(check);
	}

	@Override
	public Boolean updateOne(Role role) {
		return mapper.updateOne(role);
	}
}
