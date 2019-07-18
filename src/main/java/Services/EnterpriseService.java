package Services;

import ORM.Mapper.EnterpriseMapper;
import ORM.POJO.Enterprise;
import ORM.POJO.EnterpriseExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class EnterpriseService {
    @Resource
    EnterpriseMapper mapper;

    public List<Enterprise> listEnterprise(long offset, int limit, String nameLike) {
        EnterpriseExample example = new EnterpriseExample();
        example.setLimit(limit);
        example.setOffset(offset);
        if (StringUtils.isNotEmpty(nameLike)) {
            example.createCriteria().andNameLike("%" + nameLike + "%");
        }
        return mapper.selectByExample(example);
    }

    public long countEnterprise() {
        EnterpriseExample example = new EnterpriseExample();
        return mapper.countByExample(example);
    }
}
