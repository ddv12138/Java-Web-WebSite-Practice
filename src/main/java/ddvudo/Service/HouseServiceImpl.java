package ddvudo.Service;

import ddvudo.ORM.Mapper.HouseMapper;
import ddvudo.ORM.POJO.House;
import ddvudo.Service.Services.HouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("HouseService")
@Transactional
public class HouseServiceImpl implements HouseService {
	HouseMapper mapper;

	public HouseServiceImpl(HouseMapper mapper) {
		this.mapper = mapper;
	}

	public House selectByHouseId(String housId) {
		return mapper.selectByHouseId(housId);
	}

}
