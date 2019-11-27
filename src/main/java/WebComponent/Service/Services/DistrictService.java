package WebComponent.Service.Services;

import ORM.POJO.City;
import ORM.POJO.District;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DistrictService {

	District selectByName(String name);

	List<District> selectByCity(City city);
}
