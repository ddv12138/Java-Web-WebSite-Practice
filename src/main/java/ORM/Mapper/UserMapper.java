package ORM.Mapper;

import ORM.POJO.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from usertable where name = #{name}")
    User selectByName(String name);
}
