package ORM.Mapper;

import ORM.POJO.Enterprise;
import ORM.POJO.EnterpriseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseMapper {
    long countByExample(EnterpriseExample example);

    int deleteByExample(EnterpriseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    List<Enterprise> selectByExample(EnterpriseExample example);

    Enterprise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

    int updateByExample(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);
}