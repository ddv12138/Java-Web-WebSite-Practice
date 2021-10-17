package ddvudo.lianjia.service;


import ddvudo.lianjia.bean.City;
import ddvudo.lianjia.bean.Community;
import ddvudo.lianjia.bean.District;

import java.util.List;

public interface CommunityService {

	Community selectByName(String name);

	List<Community> selectAll();

	List<Community> selectByDistrict(District district);

	int countPreHouseNumByDistrict(District district);

	Integer countPreHouseNumByCity(City city);

	List<Community> selectHetMapDataByCity(City city);

	int updateOneLoc(Community community);

	List<Community> fetchCommunityDataByDistrictFromLianJia(District district);

}
