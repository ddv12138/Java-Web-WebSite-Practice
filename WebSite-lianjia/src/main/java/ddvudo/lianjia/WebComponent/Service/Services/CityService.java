package ddvudo.lianjia.WebComponent.Service.Services;


import ddvudo.lianjia.ORM.POJO.City;

import java.util.List;

public interface CityService {

	City selectByName(String city_name);

	int initDefaultData();

	List<City> selectAvaliableCity();
}
