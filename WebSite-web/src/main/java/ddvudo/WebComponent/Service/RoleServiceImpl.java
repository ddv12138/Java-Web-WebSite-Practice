package ddvudo.WebComponent.Service;

import ddvudo.ORM.Mapper.RoleMapper;
import ddvudo.ORM.Mapper.UserMapper;
import ddvudo.ORM.POJO.Role;
import ddvudo.ORM.POJO.User;
import ddvudo.WebComponent.Service.Services.RoleService;
import ddvudo.root.GlobalUtils.Global;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	RoleMapper mapper;
	UserMapper userMapper;

	public RoleServiceImpl(RoleMapper mapper, UserMapper userMapper) {
		this.mapper = mapper;
		this.userMapper = userMapper;
	}

	@Override
	public List<Role> listRole() {
		Global.Logger(this).info(mapper.toString());
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
		return (null != resids && resids.size() > 0) ? mapper.updateRoleResource(role, resids) : true;
	}

	@Override
	public Boolean updateRoleByUser(Integer userid, List<Integer> roleids) {
		User user = userMapper.selectById(userid);
		Assert.notNull(user, "用户不存在");
		mapper.clearRoleByUser(user);
		return (null != roleids && roleids.size() > 0) ? mapper.updateRoleByUser(user, roleids) : true;
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

	@Override
	public List<Role> listRoleByUser(Integer userid) {
		Global.Logger(this).info(mapper.toString());
		User user = userMapper.selectById(userid);
		Assert.notNull(user, "用户不存在");
		return mapper.listRoleByUser(user);
	}
}
