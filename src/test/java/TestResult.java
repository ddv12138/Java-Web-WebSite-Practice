import ORM.POJO.ResourceTable;
import org.junit.Test;

import java.util.Optional;


public class TestResult {
    @Test
    public void TestOptional() {
        ResourceTable res = new ResourceTable();
        res.setId(0);
        res.setName("asd");
        res.setCnname("asdasd");
        System.out.println(test(res));
    }

    private String test(ResourceTable t) {
        return Optional.ofNullable(t).map(r -> {
            System.out.println(r.getName());
            System.out.println(t.getName());
            return t.getName();
        }).toString();
    }
}
