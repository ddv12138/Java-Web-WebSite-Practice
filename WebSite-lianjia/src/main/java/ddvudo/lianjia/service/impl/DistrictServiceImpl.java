package ddvudo.lianjia.service.impl;

import ddvudo.lianjia.mapper.DistrictMapper;
import ddvudo.lianjia.bean.City;
import ddvudo.lianjia.bean.District;
import ddvudo.lianjia.service.DistrictService;
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
