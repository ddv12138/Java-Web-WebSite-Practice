package Services;

import ORM.Mapper.EnterpriseMapper;
import ORM.POJO.EnterpriseExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EnterpriseService {
    @Resource
    EnterpriseMapper mapper;

    public Map listEnterprise(long offset, int limit, String nameLike) {
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
