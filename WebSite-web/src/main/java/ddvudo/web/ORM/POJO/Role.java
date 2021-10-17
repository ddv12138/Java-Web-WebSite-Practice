package ddvudo.web.ORM.POJO;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
	/**
	 *
	 */
	private static final long serialVersionUID = -2820202174768533041L;
	String name, desc;
	Integer id;
	Boolean lock;

	@Override
	public String getAuthority() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}
}
