package WebComponent.Service.ServicesImpl;

import ORM.Mapper.SpittrMapper;
import ORM.POJO.Spittr;
import WebComponent.Service.Services.SpittrService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SpittrService")
@Transactional
public class SpittrServiceImpl implements SpittrService {
	SpittrMapper mapper;

	public SpittrServiceImpl(SpittrMapper mapper) {
		this.mapper = mapper;
	}

	public List<Spittr> selectLatest(int count) {
		return mapper.selectLatest(count);
	}

	@Cacheable(value = "spittrCahe")
	public Spittr selectOne(int id) {
		return mapper.selectOne(id);
	}

	public int saveOne(Spittr spittr) {
		return mapper.saveOne(spittr);
	}
}
