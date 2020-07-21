package ddvudo.Service.Services;

import ddvudo.ORM.POJO.EnterpriseRegistration;
import ddvudo.ORM.POJO.EnterpriseRegistrationExample;

import java.util.List;

public interface EnterpriseRegistrationService {


	long countByExample(EnterpriseRegistrationExample example);

	int deleteByExample(EnterpriseRegistrationExample example);

	int deleteByPrimaryKey(String id);

	int insert(EnterpriseRegistration record);

	int insertSelective(EnterpriseRegistration record);

	List<EnterpriseRegistration> selectByExample(EnterpriseRegistrationExample example);

	EnterpriseRegistration selectByPrimaryKey(String id);

	int updateByExampleSelective(EnterpriseRegistration record, EnterpriseRegistrationExample example);

	int updateByExample(EnterpriseRegistration record, EnterpriseRegistrationExample example);

	int updateByPrimaryKeySelective(EnterpriseRegistration record);

	int updateByPrimaryKey(EnterpriseRegistration record);

}


