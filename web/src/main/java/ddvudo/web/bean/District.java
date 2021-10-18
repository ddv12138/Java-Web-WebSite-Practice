package ddvudo.web.bean;

public class District {
	int uuid;
	String id, count;
	String border;
	String latitude;
	String longitude;
	String name;
	String unit_price;
	String city_id;
	String city_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"uuid\":")
				.append(uuid);
		sb.append(",\"id\":\"")
				.append(id).append('\"');
		sb.append(",\"count\":\"")
				.append(count).append('\"');
		sb.append(",\"border\":\"")
				.append(border).append('\"');
		sb.append(",\"latitude\":\"")
				.append(latitude).append('\"');
		sb.append(",\"longitude\":\"")
				.append(longitude).append('\"');
		sb.append(",\"name\":\"")
				.append(name).append('\"');
		sb.append(",\"unit_price\":\"")
				.append(unit_price).append('\"');
		sb.append(",\"city_id\":\"")
				.append(city_id).append('\"');
		sb.append(",\"city_name\":\"")
				.append(city_name).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
