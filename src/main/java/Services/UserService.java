package Services;

import Exceptions.UserAleadyExistsException;
import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import globalUtils.Global;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
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
}
