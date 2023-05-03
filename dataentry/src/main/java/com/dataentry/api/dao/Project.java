package com.dataentry.api.dao;

/**
 * 
 * @author kazi
 *
 */
public class Project {

	private String project_name;
	private String contact_point;
	private String artifacts_details;
	private String tools_and_platform;
	private String used_in_year;
	private Integer feature_reference;
	private Feature feature;

	public Project() {
		super();
	}

	public Project(String project_name, String contact_point, String artifacts_details, String tools_and_platform,
			String used_in_year, Feature feature) {
		super();
		this.project_name = project_name;
		this.contact_point = contact_point;
		this.artifacts_details = artifacts_details;
		this.tools_and_platform = tools_and_platform;
		this.used_in_year = used_in_year;
		this.feature_reference = null;
		this.feature = feature;
	}

	public Project(String project_name, String contact_point, String artifacts_details, String tools_and_platform,
			String used_in_year, Integer feature_reference, Feature feature) {
		super();
		this.project_name = project_name;
		this.contact_point = contact_point;
		this.artifacts_details = artifacts_details;
		this.tools_and_platform = tools_and_platform;
		this.used_in_year = used_in_year;
		this.feature_reference = feature_reference;
		this.feature = feature;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getContact_point() {
		return contact_point;
	}

	public void setContact_point(String contact_point) {
		this.contact_point = contact_point;
	}

	public String getArtifacts_details() {
		return artifacts_details;
	}

	public void setArtifacts_details(String artifacts_details) {
		this.artifacts_details = artifacts_details;
	}

	public String getTools_and_platform() {
		return tools_and_platform;
	}

	public void setTools_and_platform(String tools_and_platform) {
		this.tools_and_platform = tools_and_platform;
	}

	public String getUsed_in_year() {
		return used_in_year;
	}

	public void setUsed_in_year(String used_in_year) {
		this.used_in_year = used_in_year;
	}

	public Integer getFeature_reference() {
		return feature_reference;
	}

	public void setFeature_reference(Integer feature_reference) {
		this.feature_reference = feature_reference;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

}
