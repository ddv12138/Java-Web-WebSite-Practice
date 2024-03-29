package ddvudo.web.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

public class Spittr implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1152231857856479243L;
	@NotNull
	int id;
	@NotNull
	int userid;
	@NotNull
	String username;
	@Size(min = 1, max = 140)
	String content;
	Timestamp createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
}
