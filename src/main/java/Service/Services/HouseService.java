package Service.Services;

import ORM.POJO.House;
import org.springframework.stereotype.Service;
@Service
public interface HouseService {

	House selectByHouseId(String housId);

}
