package ORM.Provider;

import ORM.POJO.ResourceTable;
import org.apache.ibatis.jdbc.SQL;

public class ResourceSqlProvider {
    public String updateByExample(ResourceTable res) {
        return new SQL() {{
            UPDATE("Resourcetable");
            if (null != res.getName()) SET("name=#{name}");
            if (null != res.getCnname()) SET("cnname=#{cnname}");
            if (null != res.getIstop()) SET("istop=#{istop}");
            if (null != res.getLeftvalue()) SET("leftvalue=#{leftvalue}");
            if (null != res.getRightvalue()) SET("rightvalue=#{rightvalue}");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String selectByExample(ResourceTable res) {
        return new SQL() {{
            SELECT("*");
            FROM("Resourcetable");
            if (null != res.getId()) WHERE("id=#{id}");
            if (null != res.getName()) WHERE("name=#{name}");
            if (null != res.getCnname()) WHERE("cnname=#{cnname}");
            if (null != res.getIstop()) WHERE("istop=#{istop}");
            if (null != res.getLeftvalue()) WHERE("leftvalue=#{leftvalue}");
            if (null != res.getRightvalue()) WHERE("rightvalue=#{rightvalue}");
        }}.toString();
    }
}
