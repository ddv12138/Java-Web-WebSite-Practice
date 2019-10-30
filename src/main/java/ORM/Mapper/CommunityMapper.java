package ORM.Mapper;

import ORM.POJO.City;
import ORM.POJO.Community;
import ORM.POJO.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {


	Community selectByName(@Param("name") String name);

	List<Community> selectAll();

	List<Community> selectByDistrict(District district);

	int countPreHouseNumByDistrict(District district);

	Integer countPreHouseNumByCity(City city);

	List<Community> selectByCity(City city);
}
