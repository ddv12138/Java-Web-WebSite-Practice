package WebComponent.Service.ServicesImpl;

import Exceptions.MapperException;
import Exceptions.ServiceException;
import ORM.Mapper.ExceptionMapper;
import WebComponent.Service.Services.ExceptionService;
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
