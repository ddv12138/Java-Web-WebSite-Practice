import ORM.Mapper.UserMapper;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestMybatis {
    @Test
    public void main() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            UserMapper um = session.getMapper(UserMapper.class);
            System.out.println(um.selectByName("123"));
        }
    }
}
