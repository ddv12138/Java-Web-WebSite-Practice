package WebComponent.Service.Services;

import ORM.POJO.City;
import ORM.POJO.Community;
import ORM.POJO.District;

import java.util.List;

public interface CommunityService {

	Community selectByName(String name);

	List<Community> selectAll();

	List<Community> selectByDistrict(District district);

	int countPreHouseNumByDistrict(District district);

	Integer countPreHouseNumByCity(City city);

	List<Community> selectHetMapDataByCity(City city);

	int updateOneLoc(Community community);

}
