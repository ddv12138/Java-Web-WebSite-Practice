package ORM.Provider;

import ORM.POJO.ResourceTable;
import ORM.Utils.Condition;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;


public class ResourceSqlProvider {
    public String updateByExample(ResourceTable res) {
        return new SQL() {{
            UPDATE("Resourcetable");
            if (null != res.getName()) SET("name=#{name}");
            if (null != res.getCnname()) SET("cnname=#{cnname}");
            if (null != res.getIstop()) SET("istop=#{istop}");
            if (null != res.getUrlpath()) SET("urlpath=#{urlpath}");
            if (null != res.isHaschild()) SET("haschild=#{haschild}");
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
            if (null != res.getLevel()) WHERE("level=#{level}");
            if (null != res.getUrlpath()) WHERE("urlpath=#{urlpath}");
            if (null != res.getOrder()) WHERE("order=#{order}");
            if (null != res.isHaschild()) WHERE("haschild=#{haschild}");
        }}.toString();
    }

    public String updateByCondition(@Param("res") ResourceTable res, List<Condition> conds) {
        return new SQL() {{
            UPDATE("Resourcetable");
            if (null != res.getName()) SET("name=#{res.name}");
            if (null != res.getCnname()) SET("cnname=#{res.cnname}");
            if (null != res.getIstop()) SET("istop=#{res.istop}");
            if (null != res.getLeftvalue()) SET("leftvalue=#{res.leftvalue}");
            if (null != res.getRightvalue()) SET("rightvalue=#{res.rightvalue}");
            if (null != res.getLevel()) SET("level=#{level}");
            if (null != res.getOrder()) SET("order=#{order}");
            if (null != res.getUrlpath()) SET("urlpath=#{urlpath}");
            if (null != res.isHaschild()) SET("haschild=#{haschild}");
            for (Condition con : conds) {
                WHERE(con.toString());
            }
        }}.toString();
    }

    public String selectByCondition(List<Condition> conds) {
        return new SQL() {{
            SELECT("*");
            FROM("Resourcetable");
            for (Condition con : conds) {
                WHERE(con.toString());
            }
        }}.toString();
    }

    public String selectNextLevelNode(ResourceTable res) {
        return new SQL() {{
            SELECT("*");
            FROM("Resourcetable");
            WHERE("leftvalue>" + res.getLeftvalue());
            WHERE("rightvalue<" + res.getRightvalue());
            WHERE("level = " + (res.getLevel() + 1));
        }}.toString();
    }

    public String selectAllSubNode(ResourceTable res) {
        return new SQL() {{
            SELECT("*");
            FROM("Resourcetable");
            WHERE("leftvalue>" + res.getLeftvalue());
            WHERE("rightvalue<" + res.getRightvalue());
        }}.toString();
    }
}
