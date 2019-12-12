package WebComponent.Service.Services;

import ORM.POJO.City;

public interface CityService {

	City selectByName(String city_name);

	int initDefaultData();

}
