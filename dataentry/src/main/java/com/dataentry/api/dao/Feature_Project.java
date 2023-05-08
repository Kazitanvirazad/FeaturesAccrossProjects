package com.dataentry.api.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.dataentry.api.helpers.ResourceHelpers;

/**
 * 
 * @author kazazad
 *
 */
public class Feature_Project {
	private String id;
	private String project_name;
	private String poc;
	private String artifact_detail;
	private String feature_ref;
	private String used_year;
	private boolean feature_extended;

	public Feature_Project() {
		super();
	}

	public Feature_Project(Project project, Feature feature) {
		this();
		this.id = ResourceHelpers.generateUUID();
		this.project_name = project.getProject_name();
		this.poc = feature.getPoc();
		this.artifact_detail = feature.getArtifact_detail();
		this.feature_ref = feature.getFeature_ref();
		this.used_year = feature.getUsed_year();
		this.feature_extended = feature.getFeature_extended();
	}

	public Feature_Project(String id, String project_name, String poc, String artifact_detail, String feature_ref,
			String used_year, boolean feature_extended) {
		this();
		this.id = id;
		this.project_name = project_name;
		this.poc = poc;
		this.artifact_detail = artifact_detail;
		this.feature_ref = feature_ref;
		this.used_year = used_year;
		this.feature_extended = feature_extended;
	}

	public List<Object> createParamsList() {
		List<Object> paramList = new ArrayList<>();
		for (Field f : Feature_Project.class.getDeclaredFields()) {
			try {
				paramList.add(f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return paramList;
	}

	public String getId() {
		return id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoc() {
		return poc;
	}

	public String getArtifact_detail() {
		return artifact_detail;
	}

	public String getFeature_ref() {
		return feature_ref;
	}

	public String getUsed_year() {
		return used_year;
	}

	public boolean getFeature_extended() {
		return feature_extended;
	}

}
