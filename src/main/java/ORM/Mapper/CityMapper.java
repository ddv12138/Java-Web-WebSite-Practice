package ORM.Mapper;

import ORM.POJO.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CityMapper {

	City selectByName(@Param("city_name") String city_name);

	int initDefaultData();
}
