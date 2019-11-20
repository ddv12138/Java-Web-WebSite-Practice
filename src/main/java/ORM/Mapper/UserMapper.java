package ORM.Mapper;

import ORM.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from usertable where name = #{name}")
    User selectByName(String name);

	@Select("select * from usertable where name = #{id}")
	User selectById(String id);

	int saveOne(User user);
}
