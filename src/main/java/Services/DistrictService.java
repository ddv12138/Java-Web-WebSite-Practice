package Services;

import ORM.Mapper.DistrictMapper;
import ORM.POJO.City;
import ORM.POJO.District;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DistrictService implements DistrictMapper {
	@Resource
	DistrictMapper mapper;

	@Override
	public void createTable() {
		mapper.createTable();
	}

	@Override
	public int bathInsertList(List<District> districts) {
		int res = mapper.bathInsertList(districts);
		return res;
	}


	@Override
	public District selectByName(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public int deleteByCityId(String city_id) {
		int count = mapper.deleteByCityId(city_id);
		return count;
	}

	@Override
	public List<District> selectByCity(City city) {
		return mapper.selectByCity(city);
	}
}
