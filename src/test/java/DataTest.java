import com.alibaba.fastjson.JSON;
import ddvudo.Application;
import ddvudo.GlobalUtils.Global;
import ddvudo.ORM.POJO.WireGuardConfig;
import ddvudo.Service.Services.WireGuardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataTest {
	@Autowired
	WireGuardService wireGuardService;

	@Test
	public void test() {
		List<WireGuardConfig> configList =
				wireGuardService.newConfigList("10.1.1.0/24", 9002, 1,
						"ddvudo.buzz:9002",
						"iptables -A FORWARD -i %i -j ACCEPT; iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE",
						"iptables -D FORWARD -i %i -j ACCEPT; iptables -t nat -D POSTROUTING -o eth0 -j MASQUERADE");
		Global.Logger(this).info(JSON.toJSONString(configList));
	}
}
