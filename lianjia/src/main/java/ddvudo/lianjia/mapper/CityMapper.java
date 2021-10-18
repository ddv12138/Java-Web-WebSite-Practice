package ddvudo.lianjia.mapper;

import ddvudo.lianjia.bean.City;
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
