package WebComponent.Service.Services;

import ORM.POJO.City;
import org.springframework.stereotype.Service;
@Service
public interface CityService {

	City selectByName(String city_name);

	int initDefaultData();

}
