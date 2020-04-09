package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityMapper {

	City selectByName(@Param("city_name") String city_name);

	int initDefaultData();

	List<City> selectAvaliableCity();
}
