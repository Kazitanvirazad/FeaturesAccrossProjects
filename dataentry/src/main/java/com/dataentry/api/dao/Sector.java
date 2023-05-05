package com.dataentry.api.dao;

public class Sector {

	private String sector_name;

	public Sector(String sector_name) {
		this.sector_name = sector_name;
	}

	public Sector() {
		super();
	}

	public String getSector_name() {
		return sector_name;
	}

	public void setSector_name(String sector_name) {
		this.sector_name = sector_name;
	}

}
