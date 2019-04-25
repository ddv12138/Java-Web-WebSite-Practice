import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestMybatis {
    @Test
    public void main() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            System.out.println(session.toString());
        }
    }
}
