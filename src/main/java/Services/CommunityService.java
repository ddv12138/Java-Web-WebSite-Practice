package Services;

import ORM.Mapper.CommunityMapper;
import ORM.POJO.City;
import ORM.POJO.Community;
import ORM.POJO.District;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommunityService implements CommunityMapper {
	@Resource
	CommunityMapper mapper;

	@Override
	public void createTable() {
		mapper.createTable();
	}

	@Override
	public int bathInsertList(List<Community> communities) {
		if (communities.isEmpty()) return -1;
		int res = mapper.bathInsertList(communities);
		return res;
	}

	@Override
	public Community selectByName(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public List<Community> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<Community> selectByDistrict(District district) {
		return mapper.selectByDistrict(district);
	}

	@Override
	public int countPreHouseNumByDistrict(District district) {
		return mapper.countPreHouseNumByDistrict(district);
	}

	@Override
	public Integer countPreHouseNumByCity(City city) {
		return mapper.countPreHouseNumByCity(city);
	}

	@Override
	public List<Community> selectByCity(City city) {
		return mapper.selectByCity(city);
	}

}
