package ddvudo.web.Service.Services;

import ddvudo.web.ORM.POJO.Spittr;

import java.util.List;

public interface SpittrService {

	List<Spittr> selectLatest(int count);

	Spittr selectOne(int id);

	int saveOne(Spittr spittr);
}
