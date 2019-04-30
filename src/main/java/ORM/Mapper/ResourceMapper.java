package ORM.Mapper;

import ORM.POJO.ResourceTable;
import ORM.Provider.ResourceSqlProvider;
import ORM.Utils.Condition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ResourceMapper {
    @Insert("insert into Resourcetable(id,name,cnname,istop,leftvalue,rightvalue,level,order,urlpath) values(#{id},#{name},#{cnname},#{istop},#{leftvalue},#{rightvalue},#{level},#{order},#{urlpath})")
    int InsertByAllvalue(ResourceTable res);

    @SelectProvider(type = ResourceSqlProvider.class, method = "selectByExample")
    ResourceTable[] selectByExample(ResourceTable res);

    @SelectProvider(type = ResourceSqlProvider.class, method = "updateByCondition")
    ResourceTable[] updateByCondition(@Param("res") ResourceTable res, List<Condition> conds);

    @SelectProvider(type = ResourceSqlProvider.class, method = "selectByCondition")
    ResourceTable[] selectByCondition(List<Condition> conds);

    @Select("Select * from Resourcetable where id = #{id}")
    ResourceTable selectByID(int id);

    @SelectProvider(type = ResourceSqlProvider.class, method = "selectNextLevelNode")
    ResourceTable[] selectNextLevelNode(ResourceTable res);

    @SelectProvider(type = ResourceSqlProvider.class, method = "selectAllSubNode")
    ResourceTable[] selectAllSubNode(ResourceTable res);
}
