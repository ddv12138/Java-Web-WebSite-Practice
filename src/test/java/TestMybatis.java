import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import ORM.Utils.Condition;
import ORM.Utils.SQL_Flag;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;

public class TestMybatis {
    @Test
    public void main() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            ResourceTable rt = new ResourceTable();
            rt.setId(1);
            for (ResourceTable r : rm.selectByExample(rt)) {
                System.out.println(r);
            }
        }
    }

    @Test
    public void TestCondition() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            ResourceTable rt = new ResourceTable();
            rt.setName("1");
            Condition con = new Condition("leftvalue", 2 + "", SQL_Flag.bt);
            rm.updateByCondition(rt, Arrays.asList(new Condition[]{con}));
        }
    }
}
