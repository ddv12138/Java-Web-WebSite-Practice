package ddvudo.lianjia.WebComponent.Service;

import ddvudo.lianjia.ORM.Mapper.DistrictMapper;
import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.ORM.POJO.District;
import ddvudo.lianjia.WebComponent.Service.Services.DistrictService;
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
