package ORM.Mapper;

import ORM.POJO.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityMapper {

	City selectByName(@Param("city_name") String city_name);

	int initDefaultData();
}
