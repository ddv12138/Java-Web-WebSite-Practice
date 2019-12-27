package ORM.POJO;

public class User {
	int id;
	String name, password;
	Boolean baned, lock;

	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public Boolean getBaned() {
		return baned;
	}

	public void setBaned(Boolean baned) {
		this.baned = baned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\":")
				.append(id);
		sb.append(",\"name\":\"")
				.append(name).append('\"');
		sb.append(",\"password\":\"")
				.append(password).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
