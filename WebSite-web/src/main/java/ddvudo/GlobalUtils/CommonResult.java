package ddvudo.GlobalUtils;

import ddvudo.root.GlobalUtils.SystemCode;
import org.apache.commons.lang3.StringUtils;

public class CommonResult<T> {
	SystemCode state;
	String msg;
	T data;

	public CommonResult(SystemCode state, String msg, T data) {
		this.state = state;
		this.msg = msg;
		this.data = data;
	}


	public int getState() {
		return state.getCode();
	}

	public void setState(SystemCode state) {
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
