package WebComponent.Service.Services;

import ORM.POJO.Spittr;

import java.util.List;

public interface SpittrService {

	List<Spittr> selectLatest(int count);

	Spittr selectOne(int id);

	int saveOne(Spittr spittr);
}
