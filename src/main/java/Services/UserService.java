package Services;

import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
	@Resource
	UserMapper mapper;

	public int saveOne(User user) {
		return mapper.saveOne(user);
	}
}
