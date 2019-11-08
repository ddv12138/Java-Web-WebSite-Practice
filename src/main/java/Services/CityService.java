package Services;

import ORM.Mapper.CityMapper;
import ORM.POJO.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component
public class CityService {
	@Autowired
	CityMapper mapper;

	public City selectByName(String city_name) {
		return mapper.selectByName(city_name);
	}

	public int initDefaultData() {
		int res = mapper.initDefaultData();
		return res;
	}

}
