package ORM.Mapper;

import ORM.POJO.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface HouseMapper {

	House selectByHouseId(@Param("houseId") String houseId);

	List<House> selectHouseByCityName(String city_name);
}
