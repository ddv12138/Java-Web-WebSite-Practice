package ddvudo.Service.Services;

import ddvudo.ORM.POJO.WireGuardConfig;

import java.util.List;

public interface WireGuardService {
	List<WireGuardConfig> newConfigList(String serverCIDR, int port, int numberOfClients, String Endpoint,
										String postUpRule, String postDownRule);
}
