package WebComponent.Service.Services;

import Exceptions.UserAleadyExistsException;
import ORM.POJO.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

	int saveOne(User user) throws UserAleadyExistsException;

	User selectByName(String name);

	User selectById(int id);

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
