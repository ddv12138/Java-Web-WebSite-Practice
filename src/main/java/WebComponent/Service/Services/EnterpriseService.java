package WebComponent.Service.Services;

import java.util.Map;

public interface EnterpriseService {

	Map<String, Object> listEnterprise(long offset, int limit, String nameLike);
}
