package globalUtils;

import java.util.Arrays;

public class CommonResult {
    int state;
    String msg;
    Object[] data;

    public CommonResult(boolean state, String msg, Object[] data) {
        this.state = state?1:0;
        this.msg = msg;
        this.data = data;
    }

    public String getResult(){
        return this.toString();
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"state\":")
                .append(state);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"data\":")
                .append(Arrays.toString(data));
        sb.append('}');
        return sb.toString();
    }
}
