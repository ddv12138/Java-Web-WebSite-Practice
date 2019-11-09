package ORM.Mapper;

import ORM.POJO.City;
import ORM.POJO.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
