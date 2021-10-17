package ddvudo.web.Service;

import ddvudo.web.Exceptions.MapperException;
import ddvudo.web.Exceptions.ServiceException;
import ddvudo.web.ORM.Mapper.ExceptionMapper;
import ddvudo.web.Service.Services.ExceptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ExceptionService")
@Transactional
public class ExceptionServiceImpl implements ExceptionService {
	ExceptionMapper mapper;

	public ExceptionServiceImpl(ExceptionMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Boolean getServiceException() {
		throw new ServiceException("来自Service的Exception");
	}

	@Override
	public Boolean getMapperException() {
		throw new MapperException("来自Mapper的Exception");
	}

}
