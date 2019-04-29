package ORM.Utils;

public class Condition {
    String field, value;
    SQL_Flag flag;

    public Condition(String field, String value, SQL_Flag flag) {
        this.field = field;
        this.value = value;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return field + " " + flag.getName() + " " + value;
    }
}
