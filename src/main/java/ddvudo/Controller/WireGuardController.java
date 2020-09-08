package ddvudo.Controller;

import ddvudo.ORM.POJO.WireGuardConfig;
import ddvudo.Service.Services.WireGuardService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wg")
@Api(tags = "wireguard相关服务接口")
public class WireGuardController {
	final WireGuardService wireGuardService;

	public WireGuardController(WireGuardService wireGuardService) {
		this.wireGuardService = wireGuardService;
	}

	@PostMapping("/generatorConfig")
	@ApiOperation("生成wireguard服务端配置和相应数量的客户端配置")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "par", dataTypeClass = Map.class, examples = @Example({
					@ExampleProperty(value = "{\"dns\":\"114.114.114.114\",\"endPoint\":\"your.domian.address:9003\",\"numberOfClients\":5,\"port\":9003,\"postDownRule\":\"iptables -D FORWARD -i %i -j ACCEPT; iptables -t nat -D POSTROUTING -o eth0 -j MASQUERADE\",\"postUpRule\":\"iptables -A FORWARD -i %i -j ACCEPT; iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE\",\"serverCIDR\":\"10.0.0.0/24\"}", mediaType = "application/json")
			}))
	})
	public List<WireGuardConfig> generatorNewConfigPair(@RequestBody Map<String, String> par) {
		String ServerCIDR = par.get("serverCIDR");
		Integer port = Integer.parseInt(par.get("port"));
		Integer numberOfClients = Integer.parseInt(par.get("numberOfClients"));
		String endPoint = par.get("endPoint");

		Assert.notNull(ServerCIDR, "服务器CIDR地址为空");
		Assert.notNull(port, "服务器监听端口为空");
		Assert.notNull(numberOfClients, "客户端数量为空");
		Assert.notNull(endPoint, "客户端使用的服务器端的端点为空");
		Assert.isTrue(numberOfClients > 0, "客户端数量必须大于0");

		String dns = par.get("dns");
		String postUpRule = par.get("postUpRule");
		String postDownRule = par.get("postDownRule");

		return wireGuardService
				.newConfigList(ServerCIDR, port, numberOfClients, endPoint, dns, postUpRule, postDownRule);
	}
}
