package Services;

import ORM.Mapper.HouseMapper;
import ORM.POJO.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseService {
	@Autowired
	HouseMapper mapper;

	public House selectByHouseId(String housId) {
		return mapper.selectByHouseId(housId);
	}

}
