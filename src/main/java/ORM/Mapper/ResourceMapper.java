package ORM.Mapper;

import ORM.POJO.ResourceTable;
import ORM.Provider.ResourceSqlProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;

public interface ResourceMapper {
    @Insert("insert into Resourcetable(id,name,cnname,istop,leftvalue,rightvalue) values(#{id},#{name},#{cnname},#{istop},#{leftvalue},#{rightvalue})")
    int InsertByAllvalue(ResourceTable res);

    @SelectProvider(type = ResourceSqlProvider.class, method = "selectByExample")
    ResourceTable[] Select(ResourceTable res);
}
