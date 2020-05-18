package ddvudo.lianjia.ORM.Mapper;

import ddvudo.lianjia.ORM.POJO.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HouseMapper {

	House selectByHouseId(@Param("houseId") String houseId);

}
