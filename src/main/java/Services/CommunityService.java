package Services;

import ORM.Mapper.CommunityMapper;
import ORM.POJO.City;
import ORM.POJO.Community;
import ORM.POJO.District;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommunityService {
	@Resource
	CommunityMapper mapper;

	public Community selectByName(String name) {
		return mapper.selectByName(name);
	}

	public List<Community> selectAll() {
		return mapper.selectAll();
	}

	public List<Community> selectByDistrict(District district) {
		return mapper.selectByDistrict(district);
	}

	public int countPreHouseNumByDistrict(District district) {
		return mapper.countPreHouseNumByDistrict(district);
	}

	public Integer countPreHouseNumByCity(City city) {
		return mapper.countPreHouseNumByCity(city);
	}

	public List<Community> selectHetMapDataByCity(City city) {
		return mapper.selectHetMapDataByCity(city);
	}

}
