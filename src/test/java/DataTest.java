import GlobalUtils.Application;
import GlobalUtils.Global;
import ORM.Mapper.ResourceMapper;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class DataTest {
	@Resource
	ResourceMapper resourceMapper;

	@Test
	public void resourceTest() {
		List<ORM.POJO.Resource> res = resourceMapper.selectResourceList(-1);
		Global.Logger().info(JSON.toJSONString(res));
	}
}