package ddvudo.lianjia.service;


import ddvudo.lianjia.bean.City;

import java.util.List;

public interface CityService {

	City selectByName(String city_name);

	int initDefaultData();

	List<City> selectAvaliableCity();
}
