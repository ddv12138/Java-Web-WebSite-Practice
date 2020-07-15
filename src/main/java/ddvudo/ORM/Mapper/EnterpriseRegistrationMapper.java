package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.EnterpriseRegistration;
import ddvudo.ORM.POJO.EnterpriseRegistrationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnterpriseRegistrationMapper {
	long countByExample(EnterpriseRegistrationExample example);

	int deleteByExample(EnterpriseRegistrationExample example);

	/**
	 * delete by primary key
	 *
	 * @param id primaryKey
	 * @return deleteCount
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * insert record to table
	 *
	 * @param record the record
	 * @return insert count
	 */
	int insert(EnterpriseRegistration record);

	/**
	 * insert record to table selective
	 *
	 * @param record the record
	 * @return insert count
	 */
	int insertSelective(EnterpriseRegistration record);

	List<EnterpriseRegistration> selectByExample(EnterpriseRegistrationExample example);

	/**
	 * select by primary key
	 *
	 * @param id primary key
	 * @return object by primary key
	 */
	EnterpriseRegistration selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") EnterpriseRegistration record,
								 @Param("example") EnterpriseRegistrationExample example);

	int updateByExample(@Param("record") EnterpriseRegistration record,
						@Param("example") EnterpriseRegistrationExample example);

	/**
	 * update record selective
	 *
	 * @param record the updated record
	 * @return update count
	 */
	int updateByPrimaryKeySelective(EnterpriseRegistration record);

	/**
	 * update record
	 *
	 * @param record the updated record
	 * @return update count
	 */
	int updateByPrimaryKey(EnterpriseRegistration record);
}