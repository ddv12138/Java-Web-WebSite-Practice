package ORM.Mapper;

import ORM.POJO.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseMapper {

	House selectByHouseId(@Param("houseId") String houseId);

}
