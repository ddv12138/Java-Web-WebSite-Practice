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
public class DistrictService {
	@Resource
	DistrictMapper mapper;

	public District selectByName(String name) {
		return mapper.selectByName(name);
	}

	public List<District> selectByCity(City city) {
		return mapper.selectByCity(city);
	}
}
