package ddvudo.WebComponent.Service;

import ddvudo.ORM.Mapper.DistrictMapper;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.District;
import ddvudo.WebComponent.Service.Services.DistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("DistrictService")
@Transactional
public class DistrictServiceImpl implements DistrictService {
	DistrictMapper mapper;

	public DistrictServiceImpl(DistrictMapper mapper) {
		this.mapper = mapper;
	}

	public District selectByName(String name) {
		return mapper.selectByName(name);
	}

	public List<District> selectByCity(City city) {
		return mapper.selectByCity(city);
	}
}
