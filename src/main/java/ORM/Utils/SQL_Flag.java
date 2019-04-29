package ORM.Utils;

public enum SQL_Flag {
    bt(">"),
    eq("="),
    lt("<"),
    btc(">="),
    ltc("<="),
    neq("!=");


    private final String flag;

    SQL_Flag(String s) {
        this.flag = s;
    }

    public String getName() {
        return flag;
    }
}
