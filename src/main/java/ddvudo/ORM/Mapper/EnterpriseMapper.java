package ddvudo.ORM.Mapper;

import ddvudo.ORM.POJO.Enterprise;
import ddvudo.ORM.POJO.EnterpriseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EnterpriseMapper {
	long countByExample(EnterpriseExample example);

	int deleteByExample(EnterpriseExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Enterprise record);

	int insertSelective(Enterprise record);

	List<Enterprise> selectByExample(EnterpriseExample example);

	Enterprise selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

	int updateByExample(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

	int updateByPrimaryKeySelective(Enterprise record);

	int updateByPrimaryKey(Enterprise record);

	void selectByExample_Map_Forward(EnterpriseExample example, ResultHandler<Enterprise> handler);
}