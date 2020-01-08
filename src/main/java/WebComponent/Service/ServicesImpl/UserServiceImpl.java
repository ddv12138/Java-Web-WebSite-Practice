package WebComponent.Service.ServicesImpl;

import Exceptions.UserAleadyExistsException;
import GlobalUtils.Global;
import ORM.Mapper.RoleMapper;
import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service(value = "UserService")
@Transactional
public class UserServiceImpl implements UserService {
	UserMapper mapper;
	RoleMapper roleMapper;

	public UserServiceImpl(UserMapper mapper, RoleMapper roleMapper) {
		this.mapper = mapper;
		this.roleMapper = roleMapper;
	}

	@Override
	public int saveOne(User user) throws UserAleadyExistsException {
		if (null != mapper.selectByName(user.getName())) {
			throw new UserAleadyExistsException("user \"" + user.getName() + "\"already exists!");
		}
		Assert.notNull(user.getPassword(), "用户密码不能为空");
		if (null == user.getBaned()) {
			user.setBaned(false);
		}
		if (null == user.getLock()) {
			user.setLock(false);
		}
		user.setPassword(Global.passwdEncrypt(user.getPassword()));
		return mapper.saveOne(user);
	}

	@Override
	public User selectByName(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public User selectById(int id) {
		return mapper.selectById(id);
	}

	@Override
	public List<User> selectList(int pagenum, int pageSize) {
		return PageHelper.startPage(pagenum, pageSize).doSelectPage(() -> mapper.selectList());
	}

	@Override
	public long selectCount() {
		return PageHelper.count(() -> mapper.selectList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mapper.selectByName(username);
		Assert.notNull(user, "用户不存在");
		user.setRoles(roleMapper.listRoleByUser(user));
		return user;
	}

	@Override
	public Boolean deleteUser(User paruser) {
		Assert.notNull(paruser.getId(), "删除用户时主键id不允许为null");
		User user = mapper.selectById(paruser.getId());
		Assert.notNull(user, "用户不存在");
		return mapper.deleteOne(user);
	}

	@Override
	public Boolean userBan(User paruser, Boolean baned) {
		Assert.notNull(paruser.getId(), "此操作要求用户主键id不允许为null");
		User user = mapper.selectById(paruser.getId());
		Assert.notNull(user, "用户不存在");
		user.setBaned(baned);
		return mapper.updateOne(user);
	}


}
