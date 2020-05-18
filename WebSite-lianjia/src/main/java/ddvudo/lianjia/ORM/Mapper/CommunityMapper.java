package ddvudo.lianjia.ORM.Mapper;

import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.ORM.POJO.Community;
import ddvudo.lianjia.ORM.POJO.District;
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
