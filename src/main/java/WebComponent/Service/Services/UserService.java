package WebComponent.Service.Services;

import Exceptions.UserAleadyExistsException;
import ORM.POJO.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

	int saveOne(User user) throws UserAleadyExistsException;

	User selectByName(String name);

	User selectById(int id);

	List<User> selectList(int maxid, int limit);

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
