package Service.ServicesImpl;

import ORM.Mapper.HouseMapper;
import ORM.POJO.House;
import Service.Services.HouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("HouseService")
@Transactional
public class HouseServiceImpl implements HouseService {
	@Resource
	HouseMapper mapper;

	public House selectByHouseId(String housId) {
		return mapper.selectByHouseId(housId);
	}

}
