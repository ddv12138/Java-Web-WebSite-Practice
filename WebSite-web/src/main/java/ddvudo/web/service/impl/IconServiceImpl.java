package ddvudo.web.service.impl;

import com.github.pagehelper.PageHelper;
import ddvudo.web.mapper.IconMapper;
import ddvudo.web.service.IconService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service("IconService")
@Transactional
public class IconServiceImpl implements IconService {
	IconMapper iconMapper;

	public IconServiceImpl(IconMapper iconMapper) {
		this.iconMapper = iconMapper;
	}

	@Override
	@Cacheable(value = "iconCahe")
	public Map<String, Object> selectList(int pagenum, int pageSize) {
		Map<String, Object> res = new LinkedHashMap<>();
		res.put("data", PageHelper.startPage(pagenum, pageSize).doSelectPage(() -> iconMapper.selectList()));
		res.put("count", PageHelper.startPage(pagenum, pageSize).doCount(() -> iconMapper.selectList()));
		return res;
	}
}
