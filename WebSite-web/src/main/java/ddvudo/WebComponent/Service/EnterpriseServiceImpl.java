package ddvudo.WebComponent.Service;

import ddvudo.ORM.Mapper.EnterpriseMapper;
import ddvudo.ORM.POJO.EnterpriseExample;
import ddvudo.WebComponent.Service.Services.EnterpriseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("EnterpriseService")
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {
	EnterpriseMapper mapper;

	public EnterpriseServiceImpl(EnterpriseMapper mapper) {
		this.mapper = mapper;
	}

	public Map<String, Object> listEnterprise(long offset, int limit, String nameLike) {
		EnterpriseExample example = new EnterpriseExample();
		if (StringUtils.isNotEmpty(nameLike)) {
			example.createCriteria().andNameLike("%" + nameLike + "%");
		}
		long count = mapper.countByExample(example);
		example.setLimit(limit);
		example.setOffset(offset);
		List data = mapper.selectByExample(example);
		Map<String, Object> res = new LinkedHashMap<>();
		res.put("data", data);
		res.put("count", count);
		return res;
	}
}
