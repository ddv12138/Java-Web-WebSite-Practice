package WebComponent.Service.Services;

import ORM.POJO.Spittr;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SpittrService {

	List<Spittr> selectLatest(int count);

	@Cacheable(value = "spittrCahe")
	Spittr selectOne(int id);

	int saveOne(Spittr spittr);
}
