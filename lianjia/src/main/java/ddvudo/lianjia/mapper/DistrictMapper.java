package ddvudo.lianjia.mapper;

import ddvudo.lianjia.bean.City;
import ddvudo.lianjia.bean.District;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DistrictMapper {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
