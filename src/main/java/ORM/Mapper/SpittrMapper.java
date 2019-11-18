package ORM.Mapper;

import ORM.POJO.Spittr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpittrMapper {
	List<Spittr> selectLatest(int count);

	Spittr selectOne(int id);
}
