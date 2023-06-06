package com.dataentry.api.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.dataentry.api.helpers.ResourceHelpers;

/**
 * 
 * @author kazi
 *
 */
public class Feature {
	private String feature_ref;
	private String project_name;
	private String category;
	private String sub_category;
	private String feature_name;
	private String description;
	private String type;
	private String poc;
	private String artifact_detail;
	private String used_year;
	private boolean feature_extended;
	private String alternate_POC;

	public Feature() {
		super();
	}

	public Feature(String feature_ref, String project_name, String category, String sub_category, String feature_name,
			String description, String type, String poc, String artifact_detail, String used_year,
			boolean feature_extended, String alternate_POC) {
		super();
		this.feature_ref = feature_ref;
		this.project_name = project_name;
		this.category = category;
		this.sub_category = sub_category;
		this.feature_name = feature_name;
		this.description = description;
		this.type = type;
		this.poc = poc;
		this.artifact_detail = artifact_detail;
		this.used_year = used_year;
		this.feature_extended = feature_extended;
		this.alternate_POC = alternate_POC;
	}

	public Feature(Feature feature) {
		super();
		this.feature_ref = ResourceHelpers.generateUUID();
		this.project_name = feature.project_name;
		this.category = feature.category;
		this.sub_category = feature.sub_category;
		this.feature_name = feature.feature_name;
		this.description = feature.description;
		this.type = feature.type;
		this.poc = feature.poc;
		this.artifact_detail = feature.artifact_detail;
		this.used_year = feature.used_year;
		this.feature_extended = feature.feature_extended;
		this.alternate_POC = feature.alternate_POC;
	}

	public List<Object> createParamsList() {
		List<Object> paramList = new ArrayList<>();
		for (Field f : Feature.class.getDeclaredFields()) {
			try {
				paramList.add(f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return paramList;
	}

	public String getFeature_ref() {
		return feature_ref;
	}

	public void setFeature_ref(String feature_ref) {
		this.feature_ref = feature_ref;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public String getFeature_name() {
		return feature_name;
	}

	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoc() {
		return poc;
	}

	public void setPoc(String poc) {
		this.poc = poc;
	}

	public String getArtifact_detail() {
		return artifact_detail;
	}

	public void setArtifact_detail(String artifact_detail) {
		this.artifact_detail = artifact_detail;
	}

	public String getUsed_year() {
		return used_year;
	}

	public void setUsed_year(String used_year) {
		this.used_year = used_year;
	}

	public boolean getFeature_extended() {
		return feature_extended;
	}

	public void setFeature_extended(boolean feature_extended) {
		this.feature_extended = feature_extended;
	}

	public String getAlternate_POC() {
		return alternate_POC;
	}

	public void setAlternate_POC(String alternate_POC) {
		this.alternate_POC = alternate_POC;
	}

}
