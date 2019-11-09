package Services;

import ORM.Mapper.HouseMapper;
import ORM.POJO.House;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HouseService {
	@Resource
	HouseMapper mapper;

	public House selectByHouseId(String housId) {
		return mapper.selectByHouseId(housId);
	}

}
