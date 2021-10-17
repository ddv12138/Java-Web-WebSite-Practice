package ddvudo.lianjia.service.impl;

import ddvudo.lianjia.mapper.HouseMapper;
import ddvudo.lianjia.bean.House;
import ddvudo.lianjia.service.HouseService;
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
