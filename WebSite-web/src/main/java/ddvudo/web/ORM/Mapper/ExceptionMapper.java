package ddvudo.web.ORM.Mapper;

import ddvudo.web.Exceptions.MapperException;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class ExceptionMapper {
	public Boolean getMapperException() {
		throw new MapperException("来自mapper的异常信息");
	}
}
