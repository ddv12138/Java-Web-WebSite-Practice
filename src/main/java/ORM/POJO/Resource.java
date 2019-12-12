package ORM.POJO;

import java.util.List;

public class Resource {
	Integer id, pid, order, level;
	String name, url, levelid;
	List<Resource> childList;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLevelid() {
		return levelid;
	}

	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<Resource> getChildList() {
		return childList;
	}

	public void setChildList(List<Resource> childList) {
		this.childList = childList;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
