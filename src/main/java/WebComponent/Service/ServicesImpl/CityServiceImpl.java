package WebComponent.Service.ServicesImpl;

import ORM.Mapper.CityMapper;
import ORM.POJO.City;
import WebComponent.Service.Services.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("CityService")
@Transactional
public class CityServiceImpl implements CityService {
	@Resource
	CityMapper mapper;

	public City selectByName(String city_name) {
		return mapper.selectByName(city_name);
	}

	public int initDefaultData() {
		int res = mapper.initDefaultData();
		return res;
	}

}
