package ORM.POJO;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ResourceTable {
    @NotNull
    Integer id;
    @NotNull
    Integer istop;
    @NotNull
    Integer leftvalue;
    Integer level;
    Integer order;
    @NotNull
    private Integer rightvalue;
    @NotNull
    private String name;
    @NotNull
    private String cnname;
    @NotNull
    private String urlpath;
    Boolean haschild;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"istop\":")
                .append(istop);
        sb.append(",\"leftvalue\":")
                .append(leftvalue);
        sb.append(",\"rightvalue\":")
                .append(rightvalue);
        sb.append(",\"level\":")
                .append(level);
        sb.append(",\"order\":")
                .append(order);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"cnname\":\"")
                .append(cnname).append('\"');
        sb.append(",\"urlpath\":\"")
                .append(urlpath).append('\"');
        sb.append(",\"haschild\":")
                .append(haschild);
        sb.append('}');
        return sb.toString();
    }

    public ResourceTable() {
    }

    public Boolean isHaschild() {
        return haschild;
    }

    public void setHaschild(boolean haschild) {
        this.haschild = haschild;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        if (null == order) {
            this.order = this.leftvalue;
        } else {
            this.order = order;
        }
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Integer getLeftvalue() {
        return leftvalue;
    }

    public void setLeftvalue(Integer leftvalue) {
        this.leftvalue = leftvalue;
    }

    public Integer getRightvalue() {
        return rightvalue;
    }

    public void setRightvalue(Integer rightvalue) {
        this.rightvalue = rightvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

}
