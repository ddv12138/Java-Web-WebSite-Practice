package Services;

import Exceptions.UserAleadyExistsException;
import GlobalUtils.Global;
import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {
	@Resource
	UserMapper mapper;

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
		org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return securityUser;
	}
}
