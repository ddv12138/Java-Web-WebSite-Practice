package ORM.Mapper;

import ORM.POJO.City;
import ORM.POJO.District;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DistrictMapper {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
