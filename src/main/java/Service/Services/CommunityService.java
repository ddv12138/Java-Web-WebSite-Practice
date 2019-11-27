package Service.Services;

import ORM.POJO.City;
import ORM.POJO.Community;
import ORM.POJO.District;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommunityService {

	Community selectByName(String name);

	List<Community> selectAll();

	List<Community> selectByDistrict(District district);

	int countPreHouseNumByDistrict(District district);

	Integer countPreHouseNumByCity(City city);

	List<Community> selectHetMapDataByCity(City city);

	int updateOneLoc(Community community);

}
