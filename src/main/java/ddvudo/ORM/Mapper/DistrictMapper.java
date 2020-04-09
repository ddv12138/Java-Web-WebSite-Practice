package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.District;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DistrictMapper {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
