package ddvudo.WebComponent.Service.ServicesImpl;

import ddvudo.ORM.Mapper.CommunityMapper;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import ddvudo.ORM.POJO.District;
import ddvudo.WebComponent.Service.Services.CommunityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CommunityService")
@Transactional
public class CommunityServiceImpl implements CommunityService {
	CommunityMapper mapper;

	public CommunityServiceImpl(CommunityMapper mapper) {
		this.mapper = mapper;
	}

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

	public int updateOneLoc(Community community) {
		return mapper.updateOneLoc(community);
	}

}
