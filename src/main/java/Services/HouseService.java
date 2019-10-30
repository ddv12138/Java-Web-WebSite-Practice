package Services;

import ORM.Mapper.HouseMapper;
import ORM.POJO.House;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class HouseService implements HouseMapper {
	@Resource
	HouseMapper mapper;

	@Override
	public House selectByHouseId(String housId) {
		return mapper.selectByHouseId(housId);
	}

	@Override
	public List<House> selectHouseByCityName(String city_name) {
		return mapper.selectHouseByCityName(city_name);
	}

}
