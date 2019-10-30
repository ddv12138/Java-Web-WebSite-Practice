package ORM.Mapper;

import ORM.POJO.House;
import org.apache.ibatis.annotations.Param;

public interface HouseMapper {

	House selectByHouseId(@Param("houseId") String houseId);

}
