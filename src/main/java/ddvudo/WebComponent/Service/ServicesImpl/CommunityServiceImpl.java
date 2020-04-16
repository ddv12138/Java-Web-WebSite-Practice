package ddvudo.WebComponent.Service.ServicesImpl;

import ddvudo.ORM.Mapper.CommunityMapper;
import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import ddvudo.ORM.POJO.District;
import ddvudo.WebComponent.Service.Services.CommunityService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("CommunityService")
@Transactional
public class CommunityServiceImpl implements CommunityService {
	final CommunityMapper mapper;
	final RedisTemplate redisTemplate;

	public CommunityServiceImpl(CommunityMapper mapper, RedisTemplate redisTemplate) {
		this.mapper = mapper;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Community selectByName(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public List<Community> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<Community> selectByDistrict(District district) {
		return mapper.selectByDistrict(district);
	}

	@Override
	public int countPreHouseNumByDistrict(District district) {
		return mapper.countPreHouseNumByDistrict(district);
	}

	@Override
	public Integer countPreHouseNumByCity(City city) {
		return mapper.countPreHouseNumByCity(city);
	}

	@Override
	public List<Community> selectHetMapDataByCity(City city) {
		return mapper.selectHetMapDataByCity(city);
	}

	@Override
	public int updateOneLoc(Community community) {
		return mapper.updateOneLoc(community);
	}

	@Override
	public void setLastUpdateTime() {
		SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		redisTemplate.opsForValue().set("lianjiaupdatetime", ymdhms.format(new Date()));
	}

}
