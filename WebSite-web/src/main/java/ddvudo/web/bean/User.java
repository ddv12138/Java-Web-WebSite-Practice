package ddvudo.web.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
	/**
	 *
	 */
	private static final long serialVersionUID = 2476687366466546959L;
	Integer id;
	String name, password;
	Boolean baned, lock;
	List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getBaned() {
		return baned;
	}

	public void setBaned(Boolean baned) {
		this.baned = baned;
	}

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !(null != baned && baned);
	}

	@Override
	public boolean isAccountNonLocked() {
		return !(null != baned && baned);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !(null != baned && baned);
	}

	@Override
	public boolean isEnabled() {
		return !(null != baned && baned);
	}
}
