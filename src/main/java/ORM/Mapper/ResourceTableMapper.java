package ORM.Mapper;

import ORM.POJO.ResourceTable;
import ORM.Provider.ResourceSqlProvider;
import ORM.Utils.Condition;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceTableMapper {
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

	@UpdateProvider(type = ResourceSqlProvider.class, method = "updateByExample")
	int updateByExample(ResourceTable res);

	Integer insertByParent(@Param("pnode") ResourceTable pnode, @Param("node") ResourceTable node);

	Integer deleteRes(@Param("node") ResourceTable node, @Param("offset") int offset);

	@Select("Select * from Resourcetable where id = #{id}")
	Map selectMapByID(int id);

	@MapKey("id")
	Map listResource();
}
