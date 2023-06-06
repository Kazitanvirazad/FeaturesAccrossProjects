package com.dataentry.api.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kazi
 *
 */
public class Project {

	private String project_name;
	private boolean regionUS;
	private boolean regionCA;
	private boolean regionEU;
	private boolean regionAUNZ;
	private boolean regionChina;
	private String sector;
	private String project_contact_point;
	private boolean multi_brand;
	private String brandnames;
	private boolean multi_site;
	private String project_notes;
	private String last_served_year;
	private String client_base;
	private String practice;
	private String domain;

	public Project() {
		super();
	}

	public Project(String project_name, boolean regionUS, boolean regionCA, boolean regionEU, boolean regionAUNZ,
			boolean regionChina, String sector, String project_contact_point, boolean multi_brand, String brandnames,
			boolean multi_site, String project_notes, String last_served_year, String client_base, String practice,
			String domain) {
		super();
		this.project_name = project_name;
		this.regionUS = regionUS;
		this.regionCA = regionCA;
		this.regionEU = regionEU;
		this.regionAUNZ = regionAUNZ;
		this.regionChina = regionChina;
		this.sector = sector;
		this.project_contact_point = project_contact_point;
		this.multi_brand = multi_brand;
		this.brandnames = brandnames;
		this.multi_site = multi_site;
		this.project_notes = project_notes;
		this.last_served_year = last_served_year;
		this.client_base = client_base;
		this.practice = practice;
		this.domain = domain;
	}

	public List<Object> createParamsList() {
		List<Object> paramList = new ArrayList<>();
		for (Field f : Project.class.getDeclaredFields()) {
			try {
				paramList.add(f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return paramList;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public boolean isRegionUS() {
		return regionUS;
	}

	public void setRegionUS(boolean regionUS) {
		this.regionUS = regionUS;
	}

	public boolean isRegionCA() {
		return regionCA;
	}

	public void setRegionCA(boolean regionCA) {
		this.regionCA = regionCA;
	}

	public boolean isRegionEU() {
		return regionEU;
	}

	public void setRegionEU(boolean regionEU) {
		this.regionEU = regionEU;
	}

	public boolean isRegionAUNZ() {
		return regionAUNZ;
	}

	public void setRegionAUNZ(boolean regionAUNZ) {
		this.regionAUNZ = regionAUNZ;
	}

	public boolean isRegionChina() {
		return regionChina;
	}

	public void setRegionChina(boolean regionChina) {
		this.regionChina = regionChina;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getProject_contact_point() {
		return project_contact_point;
	}

	public void setProject_contact_point(String project_contact_point) {
		this.project_contact_point = project_contact_point;
	}

	public boolean isMulti_brand() {
		return multi_brand;
	}

	public void setMulti_brand(boolean multi_brand) {
		this.multi_brand = multi_brand;
	}

	public String getBrandnames() {
		return brandnames;
	}

	public void setBrandnames(String brandnames) {
		this.brandnames = brandnames;
	}

	public boolean isMulti_site() {
		return multi_site;
	}

	public void setMulti_site(boolean multi_site) {
		this.multi_site = multi_site;
	}

	public String getProject_notes() {
		return project_notes;
	}

	public void setProject_notes(String project_notes) {
		this.project_notes = project_notes;
	}

	public String getLast_served_year() {
		return last_served_year;
	}

	public void setLast_served_year(String last_served_year) {
		this.last_served_year = last_served_year;
	}

	public String getClient_base() {
		return client_base;
	}

	public void setClient_base(String client_base) {
		this.client_base = client_base;
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

}
