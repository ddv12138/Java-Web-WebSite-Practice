package WebComponent.Service.Services;

import ORM.POJO.City;
import ORM.POJO.District;

import java.util.List;

public interface DistrictService {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
