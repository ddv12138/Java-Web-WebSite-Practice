package ddvudo.lianjia.WebComponent.Service.Services;


import ddvudo.lianjia.ORM.POJO.City;
import ddvudo.lianjia.ORM.POJO.District;

import java.util.List;

public interface DistrictService {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
