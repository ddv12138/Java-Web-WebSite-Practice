package ORM.Mapper;

import ORM.POJO.City;
import ORM.POJO.District;

import java.util.List;

public interface DistrictMapper {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
