package ORM.Mapper;

import ORM.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
	User selectByName(String name);

	User selectById(int id);

	List<User> selectList(@Param("maxid") int maxid, @Param("limit") int limit);

	int saveOne(User user);
}
