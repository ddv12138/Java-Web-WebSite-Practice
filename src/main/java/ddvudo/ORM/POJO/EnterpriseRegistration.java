package ddvudo.ORM.POJO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ddvudo-ORM-POJO-EnterpriseRegistration")
public class EnterpriseRegistration {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "")
	private String name;

	@ApiModelProperty(value = "")
	private String code;

	@ApiModelProperty(value = "")
	private String regday;

	@ApiModelProperty(value = "")
	private String character;

	@ApiModelProperty(value = "")
	private String legalrepresentative;

	@ApiModelProperty(value = "")
	private String capital;

	@ApiModelProperty(value = "")
	private String businessscope;

	@ApiModelProperty(value = "")
	private String province;

	@ApiModelProperty(value = "")
	private String city;

	@ApiModelProperty(value = "")
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegday() {
		return regday;
	}

	public void setRegday(String regday) {
		this.regday = regday;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getLegalrepresentative() {
		return legalrepresentative;
	}

	public void setLegalrepresentative(String legalrepresentative) {
		this.legalrepresentative = legalrepresentative;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getBusinessscope() {
		return businessscope;
	}

	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", code=").append(code);
		sb.append(", regday=").append(regday);
		sb.append(", character=").append(character);
		sb.append(", legalrepresentative=").append(legalrepresentative);
		sb.append(", capital=").append(capital);
		sb.append(", businessscope=").append(businessscope);
		sb.append(", province=").append(province);
		sb.append(", city=").append(city);
		sb.append(", address=").append(address);
		sb.append("]");
		return sb.toString();
	}
}