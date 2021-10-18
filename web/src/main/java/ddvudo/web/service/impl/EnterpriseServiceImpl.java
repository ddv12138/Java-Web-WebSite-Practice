package ddvudo.web.service.impl;

import ddvudo.web.mapper.EnterpriseMapper;
import ddvudo.web.bean.Enterprise;
import ddvudo.web.bean.EnterpriseExample;
import ddvudo.web.service.EnterpriseService;
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
		List<Enterprise> data = mapper.selectByExample(example);
		Map<String, Object> res = new LinkedHashMap<>();
		res.put("data", data);
		res.put("count", count);
		return res;
	}
}
