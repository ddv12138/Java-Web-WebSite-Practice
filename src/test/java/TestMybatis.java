import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import ORM.Utils.Condition;
import ORM.Utils.ResourceUtil;
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

    @Test
    public void InsertNodeByParent() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            ResourceTable parent = rm.selectByID(2);
            ResourceTable node = ResourceUtil.getInstance().getNewSubNode(parent, "1q2w", "yuio", 0, null, null, false);
            System.out.println(node);
            System.out.println(rm.insertByParent(parent, node));
            session.commit();
        }
    }

    @Test
    public void DeleteRes() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            ResourceTable parent = rm.selectByID(20);
            System.out.println(ResourceUtil.getInstance().deleteResource(parent, session));
            session.commit();
        }
    }

    @Test
    public void TestReturnTypes() {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            ResourceMapper rm = session.getMapper(ResourceMapper.class);
            System.out.println(rm.listResource());
            session.commit();
        }
    }
}
