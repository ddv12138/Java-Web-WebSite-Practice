package ddvudo.WebComponent.Service.Services;

import ddvudo.ORM.POJO.City;
import ddvudo.ORM.POJO.District;

import java.util.List;

public interface DistrictService {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
