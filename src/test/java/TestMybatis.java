import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestMybatis {
    @Test
    public void main() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            ResourceTable rt = new ResourceTable();
            rt.setId(1);
            for (ResourceTable r : rm.Select(rt)) {
                System.out.println(r);
            }
        }
    }
}
