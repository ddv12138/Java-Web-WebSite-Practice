package ddvudo.web.service.impl;

import ddvudo.web.exception.MapperException;
import ddvudo.web.exception.ServiceException;
import ddvudo.web.mapper.ExceptionMapper;
import ddvudo.web.service.ExceptionService;
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
