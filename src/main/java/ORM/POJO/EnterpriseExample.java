package ORM.POJO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnterpriseExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	private Integer limit;

	private Long offset;

	public EnterpriseExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("Id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("Id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("Id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("Id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("Id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("Id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("Id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("Id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("Id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("Id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("Id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("Id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("`name` is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("`name` is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("`name` =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("`name` <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("`name` >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("`name` >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("`name` <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("`name` <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("`name` like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("`name` not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("`name` in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("`name` not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("`name` between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("`name` not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andCodeIsNull() {
			addCriterion("code is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("code is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(String value) {
			addCriterion("code =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(String value) {
			addCriterion("code <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(String value) {
			addCriterion("code >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(String value) {
			addCriterion("code >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(String value) {
			addCriterion("code <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(String value) {
			addCriterion("code <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLike(String value) {
			addCriterion("code like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotLike(String value) {
			addCriterion("code not like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<String> values) {
			addCriterion("code in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<String> values) {
			addCriterion("code not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(String value1, String value2) {
			addCriterion("code between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(String value1, String value2) {
			addCriterion("code not between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andRegdayIsNull() {
			addCriterion("regday is null");
			return (Criteria) this;
		}

		public Criteria andRegdayIsNotNull() {
			addCriterion("regday is not null");
			return (Criteria) this;
		}

		public Criteria andRegdayEqualTo(Date value) {
			addCriterion("regday =", value, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayNotEqualTo(Date value) {
			addCriterion("regday <>", value, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayGreaterThan(Date value) {
			addCriterion("regday >", value, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayGreaterThanOrEqualTo(Date value) {
			addCriterion("regday >=", value, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayLessThan(Date value) {
			addCriterion("regday <", value, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayLessThanOrEqualTo(Date value) {
			addCriterion("regday <=", value, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayIn(List<Date> values) {
			addCriterion("regday in", values, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayNotIn(List<Date> values) {
			addCriterion("regday not in", values, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayBetween(Date value1, Date value2) {
			addCriterion("regday between", value1, value2, "regday");
			return (Criteria) this;
		}

		public Criteria andRegdayNotBetween(Date value1, Date value2) {
			addCriterion("regday not between", value1, value2, "regday");
			return (Criteria) this;
		}

		public Criteria andCharacterIsNull() {
			addCriterion("`character` is null");
			return (Criteria) this;
		}

		public Criteria andCharacterIsNotNull() {
			addCriterion("`character` is not null");
			return (Criteria) this;
		}

		public Criteria andCharacterEqualTo(String value) {
			addCriterion("`character` =", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterNotEqualTo(String value) {
			addCriterion("`character` <>", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterGreaterThan(String value) {
			addCriterion("`character` >", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterGreaterThanOrEqualTo(String value) {
			addCriterion("`character` >=", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterLessThan(String value) {
			addCriterion("`character` <", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterLessThanOrEqualTo(String value) {
			addCriterion("`character` <=", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterLike(String value) {
			addCriterion("`character` like", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterNotLike(String value) {
			addCriterion("`character` not like", value, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterIn(List<String> values) {
			addCriterion("`character` in", values, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterNotIn(List<String> values) {
			addCriterion("`character` not in", values, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterBetween(String value1, String value2) {
			addCriterion("`character` between", value1, value2, "character");
			return (Criteria) this;
		}

		public Criteria andCharacterNotBetween(String value1, String value2) {
			addCriterion("`character` not between", value1, value2, "character");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeIsNull() {
			addCriterion("legalRepresentative is null");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeIsNotNull() {
			addCriterion("legalRepresentative is not null");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeEqualTo(String value) {
			addCriterion("legalRepresentative =", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeNotEqualTo(String value) {
			addCriterion("legalRepresentative <>", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeGreaterThan(String value) {
			addCriterion("legalRepresentative >", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeGreaterThanOrEqualTo(String value) {
			addCriterion("legalRepresentative >=", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeLessThan(String value) {
			addCriterion("legalRepresentative <", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeLessThanOrEqualTo(String value) {
			addCriterion("legalRepresentative <=", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeLike(String value) {
			addCriterion("legalRepresentative like", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeNotLike(String value) {
			addCriterion("legalRepresentative not like", value, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeIn(List<String> values) {
			addCriterion("legalRepresentative in", values, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeNotIn(List<String> values) {
			addCriterion("legalRepresentative not in", values, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeBetween(String value1, String value2) {
			addCriterion("legalRepresentative between", value1, value2, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andLegalrepresentativeNotBetween(String value1, String value2) {
			addCriterion("legalRepresentative not between", value1, value2, "legalrepresentative");
			return (Criteria) this;
		}

		public Criteria andCapitalIsNull() {
			addCriterion("capital is null");
			return (Criteria) this;
		}

		public Criteria andCapitalIsNotNull() {
			addCriterion("capital is not null");
			return (Criteria) this;
		}

		public Criteria andCapitalEqualTo(String value) {
			addCriterion("capital =", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalNotEqualTo(String value) {
			addCriterion("capital <>", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalGreaterThan(String value) {
			addCriterion("capital >", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalGreaterThanOrEqualTo(String value) {
			addCriterion("capital >=", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalLessThan(String value) {
			addCriterion("capital <", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalLessThanOrEqualTo(String value) {
			addCriterion("capital <=", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalLike(String value) {
			addCriterion("capital like", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalNotLike(String value) {
			addCriterion("capital not like", value, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalIn(List<String> values) {
			addCriterion("capital in", values, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalNotIn(List<String> values) {
			addCriterion("capital not in", values, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalBetween(String value1, String value2) {
			addCriterion("capital between", value1, value2, "capital");
			return (Criteria) this;
		}

		public Criteria andCapitalNotBetween(String value1, String value2) {
			addCriterion("capital not between", value1, value2, "capital");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeIsNull() {
			addCriterion("businessScope is null");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeIsNotNull() {
			addCriterion("businessScope is not null");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeEqualTo(String value) {
			addCriterion("businessScope =", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeNotEqualTo(String value) {
			addCriterion("businessScope <>", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeGreaterThan(String value) {
			addCriterion("businessScope >", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeGreaterThanOrEqualTo(String value) {
			addCriterion("businessScope >=", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeLessThan(String value) {
			addCriterion("businessScope <", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeLessThanOrEqualTo(String value) {
			addCriterion("businessScope <=", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeLike(String value) {
			addCriterion("businessScope like", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeNotLike(String value) {
			addCriterion("businessScope not like", value, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeIn(List<String> values) {
			addCriterion("businessScope in", values, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeNotIn(List<String> values) {
			addCriterion("businessScope not in", values, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeBetween(String value1, String value2) {
			addCriterion("businessScope between", value1, value2, "businessscope");
			return (Criteria) this;
		}

		public Criteria andBusinessscopeNotBetween(String value1, String value2) {
			addCriterion("businessScope not between", value1, value2, "businessscope");
			return (Criteria) this;
		}

		public Criteria andProvinceIsNull() {
			addCriterion("province is null");
			return (Criteria) this;
		}

		public Criteria andProvinceIsNotNull() {
			addCriterion("province is not null");
			return (Criteria) this;
		}

		public Criteria andProvinceEqualTo(String value) {
			addCriterion("province =", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotEqualTo(String value) {
			addCriterion("province <>", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceGreaterThan(String value) {
			addCriterion("province >", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceGreaterThanOrEqualTo(String value) {
			addCriterion("province >=", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceLessThan(String value) {
			addCriterion("province <", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceLessThanOrEqualTo(String value) {
			addCriterion("province <=", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceLike(String value) {
			addCriterion("province like", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotLike(String value) {
			addCriterion("province not like", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceIn(List<String> values) {
			addCriterion("province in", values, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotIn(List<String> values) {
			addCriterion("province not in", values, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceBetween(String value1, String value2) {
			addCriterion("province between", value1, value2, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotBetween(String value1, String value2) {
			addCriterion("province not between", value1, value2, "province");
			return (Criteria) this;
		}

		public Criteria andCityIsNull() {
			addCriterion("city is null");
			return (Criteria) this;
		}

		public Criteria andCityIsNotNull() {
			addCriterion("city is not null");
			return (Criteria) this;
		}

		public Criteria andCityEqualTo(String value) {
			addCriterion("city =", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotEqualTo(String value) {
			addCriterion("city <>", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityGreaterThan(String value) {
			addCriterion("city >", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityGreaterThanOrEqualTo(String value) {
			addCriterion("city >=", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLessThan(String value) {
			addCriterion("city <", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLessThanOrEqualTo(String value) {
			addCriterion("city <=", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLike(String value) {
			addCriterion("city like", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotLike(String value) {
			addCriterion("city not like", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityIn(List<String> values) {
			addCriterion("city in", values, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotIn(List<String> values) {
			addCriterion("city not in", values, "city");
			return (Criteria) this;
		}

		public Criteria andCityBetween(String value1, String value2) {
			addCriterion("city between", value1, value2, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotBetween(String value1, String value2) {
			addCriterion("city not between", value1, value2, "city");
			return (Criteria) this;
		}

		public Criteria andAddressIsNull() {
			addCriterion("address is null");
			return (Criteria) this;
		}

		public Criteria andAddressIsNotNull() {
			addCriterion("address is not null");
			return (Criteria) this;
		}

		public Criteria andAddressEqualTo(String value) {
			addCriterion("address =", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotEqualTo(String value) {
			addCriterion("address <>", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThan(String value) {
			addCriterion("address >", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThanOrEqualTo(String value) {
			addCriterion("address >=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThan(String value) {
			addCriterion("address <", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThanOrEqualTo(String value) {
			addCriterion("address <=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLike(String value) {
			addCriterion("address like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotLike(String value) {
			addCriterion("address not like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressIn(List<String> values) {
			addCriterion("address in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotIn(List<String> values) {
			addCriterion("address not in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressBetween(String value1, String value2) {
			addCriterion("address between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotBetween(String value1, String value2) {
			addCriterion("address not between", value1, value2, "address");
			return (Criteria) this;
		}
	}

	/**
	 *
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}
	}
}