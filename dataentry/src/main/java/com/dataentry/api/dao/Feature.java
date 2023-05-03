package com.dataentry.api.dao;

/**
 * 
 * @author kazi
 *
 */
public class Feature {
	private Integer feature_reference;
	private String practice;
	private String domain;
	private String sector;
	private String category;
	private String sub_category;
	private String feature_short_name;
	private String feature_description;

	public Feature() {
		super();
	}

	public Feature(Integer feature_reference, String practice, String domain, String sector, String category,
			String sub_category, String feature_short_name) {
		super();
		this.feature_reference = feature_reference;
		this.practice = practice;
		this.domain = domain;
		this.sector = sector;
		this.category = category;
		this.sub_category = sub_category;
		this.feature_short_name = feature_short_name;
		this.feature_description = this.category + "-" + this.sub_category + "-" + this.feature_short_name;
	}

	public Feature(String practice, String domain, String sector, String category, String sub_category,
			String feature_short_name) {
		super();
		this.feature_reference = null;
		this.practice = practice;
		this.domain = domain;
		this.sector = sector;
		this.category = category;
		this.sub_category = sub_category;
		this.feature_short_name = feature_short_name;
		this.feature_description = this.category + "-" + this.sub_category + "-" + this.feature_short_name;
	}

	public Feature(Integer feature_reference, String practice, String domain, String sector, String category,
			String sub_category, String feature_short_name, String feature_description) {
		super();
		this.feature_reference = feature_reference;
		this.practice = practice;
		this.domain = domain;
		this.sector = sector;
		this.category = category;
		this.sub_category = sub_category;
		this.feature_short_name = feature_short_name;
		this.feature_description = feature_description;
	}

	public Integer getFeature_reference() {
		return feature_reference;
	}

	public void setFeature_reference(Integer feature_reference) {
		this.feature_reference = feature_reference;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public String getFeature_short_name() {
		return feature_short_name;
	}

	public void setFeature_short_name(String feature_short_name) {
		this.feature_short_name = feature_short_name;
	}

	public String getFeature_description() {
		return feature_description;
	}

	public void setFeature_description(String feature_description) {
		this.feature_description = feature_description;
	}

}
