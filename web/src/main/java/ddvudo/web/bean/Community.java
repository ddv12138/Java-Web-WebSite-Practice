package ddvudo.web.bean;

public class Community {
	int unit_price, count, uuid;
	String name, district_name, city_name, longitude, latitude, id, district_id, city_id, gaode_lng, gaode_lat;

	public String getGaode_lng() {
		return gaode_lng;
	}

	public void setGaode_lng(String gaode_lng) {
		this.gaode_lng = gaode_lng;
	}

	public String getGaode_lat() {
		return gaode_lat;
	}

	public void setGaode_lat(String gaode_lat) {
		this.gaode_lat = gaode_lat;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public int getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"unit_price\":")
				.append(unit_price);
		sb.append(",\"count\":")
				.append(count);
		sb.append(",\"uuid\":")
				.append(uuid);
		sb.append(",\"name\":\"")
				.append(name).append('\"');
		sb.append(",\"district_name\":\"")
				.append(district_name).append('\"');
		sb.append(",\"city_name\":\"")
				.append(city_name).append('\"');
		sb.append(",\"longitude\":\"")
				.append(longitude).append('\"');
		sb.append(",\"latitude\":\"")
				.append(latitude).append('\"');
		sb.append(",\"id\":\"")
				.append(id).append('\"');
		sb.append(",\"district_id\":\"")
				.append(district_id).append('\"');
		sb.append(",\"city_id\":\"")
				.append(city_id).append('\"');
		sb.append(",\"gaode_lng\":\"")
				.append(gaode_lng).append('\"');
		sb.append(",\"gaode_lat\":\"")
				.append(gaode_lat).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
