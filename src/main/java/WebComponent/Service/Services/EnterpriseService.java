package WebComponent.Service.Services;

import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface EnterpriseService {

	Map<String, Object> listEnterprise(long offset, int limit, String nameLike);
}
