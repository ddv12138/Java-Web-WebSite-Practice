package WebComponent.Service.ServicesImpl;

import Exceptions.UserAleadyExistsException;
import GlobalUtils.Global;
import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import WebComponent.Service.Services.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "UserService")
@Transactional
public class UserServiceImpl implements UserService {
	UserMapper mapper;

	public UserServiceImpl(UserMapper mapper) {
		this.mapper = mapper;
	}

	public int saveOne(User user) throws UserAleadyExistsException {
		if (null != mapper.selectByName(user.getName())) {
			throw new UserAleadyExistsException("user \"" + user.getName() + "\"already exists!");
		}
		user.setPassword(Global.passwdEncrypt(user.getPassword()));
		return mapper.saveOne(user);
	}

	public User selectByName(String name) {
		return mapper.selectByName(name);
	}

	public User selectById(int id) {
		return mapper.selectById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mapper.selectByName(username);
		if (null == user)
			throw new UsernameNotFoundException("用户不存在");
		return new org.springframework.security.core.userdetails.User
				(user.getName(), user.getPassword(),
						AuthorityUtils.createAuthorityList("spittr"));
	}
}
