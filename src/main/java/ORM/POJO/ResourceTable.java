package ORM.POJO;

public class ResourceTable {
    Integer id, istop, leftvalue, rightvalue;
    String name, cnname;

    public ResourceTable() {
    }

    public ResourceTable(Integer istop, String name, String cnname) {
        this.istop = istop;
        this.name = name;
        this.cnname = cnname;
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
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"cnname\":\"")
                .append(cnname).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
