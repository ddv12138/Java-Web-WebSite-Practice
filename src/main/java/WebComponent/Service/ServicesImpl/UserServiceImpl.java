package WebComponent.Service.ServicesImpl;

import Exceptions.UserAleadyExistsException;
import GlobalUtils.Global;
import ORM.Mapper.RoleMapper;
import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<User> selectList(int maxid, int limit) {
		return mapper.selectList(maxid, limit);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mapper.selectByName(username);
		user.setRoles(roleMapper.listRoleByUser(user));
		if (null == user)
			throw new UsernameNotFoundException("用户不存在");
		return user;
	}
}
