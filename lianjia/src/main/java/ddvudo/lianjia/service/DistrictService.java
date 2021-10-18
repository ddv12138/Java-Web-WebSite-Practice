package ddvudo.lianjia.service;


import ddvudo.lianjia.bean.City;
import ddvudo.lianjia.bean.District;

import java.util.List;

public interface DistrictService {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
