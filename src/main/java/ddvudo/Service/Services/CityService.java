package ddvudo.Service.Services;

import ddvudo.ORM.POJO.City;

import java.util.List;

public interface CityService {

	City selectByName(String city_name);

	int initDefaultData();

	List<City> selectAvaliableCity();
}
