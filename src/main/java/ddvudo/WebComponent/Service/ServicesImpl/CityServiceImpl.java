package ddvudo.WebComponent.Service.ServicesImpl;

import ddvudo.ORM.Mapper.CityMapper;
import ddvudo.ORM.POJO.City;
import ddvudo.WebComponent.Service.Services.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CityService")
@Transactional
public class CityServiceImpl implements CityService {
	CityMapper mapper;

	public CityServiceImpl(CityMapper mapper) {
		this.mapper = mapper;
	}

	public City selectByName(String city_name) {
		return mapper.selectByName(city_name);
	}

	public int initDefaultData() {
		return mapper.initDefaultData();
	}

	@Override
	public List<City> selectAvaliableCity() {
		return mapper.selectAvaliableCity();
	}

}
