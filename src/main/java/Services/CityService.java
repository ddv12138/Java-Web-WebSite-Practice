package Services;

import ORM.Mapper.CityMapper;
import ORM.POJO.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CityService implements CityMapper {
	@Resource
	CityMapper mapper;

	@Override
	public void createTable() {
		mapper.createTable();
	}

	@Override
	public int insert(City city) {
		int res = mapper.insert(city);
		return res;
	}

	@Override
	public City selectByName(String city_name) {
		return mapper.selectByName(city_name);
	}

	@Override
	public int initDefaultData() {
		int res = mapper.initDefaultData();
		return res;
	}

}
