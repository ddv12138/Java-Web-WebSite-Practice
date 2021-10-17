package ddvudo.web.mapper;

import ddvudo.web.bean.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface IconMapper {
	Collection<Icon> selectList();
}
