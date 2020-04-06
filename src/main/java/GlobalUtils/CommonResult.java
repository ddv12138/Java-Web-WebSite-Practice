package GlobalUtils;

import org.apache.commons.lang3.StringUtils;

public class CommonResult {
	int state;
	String msg;
	Object data;

	public CommonResult(boolean state, String msg, Object data) {
		this.state = state ? 1 : 0;
		this.msg = msg;
		this.data = data;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		if (StringUtils.isEmpty(this.msg)) {
			return "success";
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
