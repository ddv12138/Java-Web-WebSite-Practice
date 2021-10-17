package ddvudo.web.Service.Services;

import ddvudo.web.Exceptions.UserAleadyExistsException;
import ddvudo.web.ORM.POJO.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

	int saveOne(User user) throws UserAleadyExistsException;

	User selectByName(String name);

	User selectById(int id);

	List<User> selectList(int maxid, int limit);

	long selectCount();

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	Boolean deleteUser(User user);

	Boolean userBan(User paruser, Boolean baned);

	boolean authUser(User user);
}
