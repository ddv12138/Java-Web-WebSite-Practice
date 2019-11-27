package Service.Services;

import ORM.POJO.Spittr;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SpittrService {

	List<Spittr> selectLatest(int count);

	Spittr selectOne(int id);

	int saveOne(Spittr spittr);
}
