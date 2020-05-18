package ddvudo.lianjia.WebComponent.Service;

import ddvudo.lianjia.ORM.Mapper.HouseMapper;
import ddvudo.lianjia.ORM.POJO.House;
import ddvudo.lianjia.WebComponent.Service.Services.HouseService;
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
