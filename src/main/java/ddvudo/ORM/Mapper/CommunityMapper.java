package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.Community;
import ddvudo.ORM.POJO.District;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommunityMapper {


	Community selectByName(@Param("name") String name);

	List<Community> selectAll();

	List<Community> selectByDistrict(District district);

	int countPreHouseNumByDistrict(District district);

	Integer countPreHouseNumByCity(City city);

	List<Community> selectHetMapDataByCity(City city);

	int updateOneLoc(Community community);

	int bathInsertList(List<Community> communities);

	int deleteByDistrict(District district);
}
