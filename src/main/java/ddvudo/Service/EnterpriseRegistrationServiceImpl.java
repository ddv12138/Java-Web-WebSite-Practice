package ddvudo.Service;

import ddvudo.ORM.Mapper.EnterpriseRegistrationMapper;
import ddvudo.ORM.POJO.EnterpriseRegistration;
import ddvudo.ORM.POJO.EnterpriseRegistrationExample;
import ddvudo.Service.Services.EnterpriseRegistrationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnterpriseRegistrationServiceImpl implements EnterpriseRegistrationService {

	@Resource
	private EnterpriseRegistrationMapper enterpriseRegistrationMapper;

	@Override
	public long countByExample(EnterpriseRegistrationExample example) {
		return enterpriseRegistrationMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(EnterpriseRegistrationExample example) {
		return enterpriseRegistrationMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return enterpriseRegistrationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EnterpriseRegistration record) {
		return enterpriseRegistrationMapper.insert(record);
	}

	@Override
	public int insertSelective(EnterpriseRegistration record) {
		return enterpriseRegistrationMapper.insertSelective(record);
	}

	@Override
	public List<EnterpriseRegistration> selectByExample(EnterpriseRegistrationExample example) {
		return enterpriseRegistrationMapper.selectByExample(example);
	}

	@Override
	public EnterpriseRegistration selectByPrimaryKey(String id) {
		return enterpriseRegistrationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(EnterpriseRegistration record, EnterpriseRegistrationExample example) {
		return enterpriseRegistrationMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(EnterpriseRegistration record, EnterpriseRegistrationExample example) {
		return enterpriseRegistrationMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(EnterpriseRegistration record) {
		return enterpriseRegistrationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EnterpriseRegistration record) {
		return enterpriseRegistrationMapper.updateByPrimaryKey(record);
	}

}


