package ddvudo.web.mapper;

import ddvudo.web.bean.Spittr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpittrMapper {
	List<Spittr> selectLatest(int count);

	Spittr selectOne(int id);

	int saveOne(@Param("Spittr") Spittr spittr);
}
