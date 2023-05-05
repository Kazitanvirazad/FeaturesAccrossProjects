create table feature(
  	feature_reference serial UNIQUE NOT NULL,
  	practice varchar(100) NOT NULL,
 	domain varchar(100) NOT NULL,
  	sector varchar(100) NOT NULL,
  	category varchar(100) NOT NULL,
  	sub_category varchar(100) NOT NULL,
  	feature_short_name varchar(100) NOT NULL,
  	feature_description varchar(300) NOT NULL,
  	CONSTRAINT PK_feature_feature_reference PRIMARY KEY(feature_reference)
);
ALTER SEQUENCE feature_feature_reference_seq RESTART WITH 100;

create table project(
 	project_name VARCHAR(100) UNIQUE NOT NULL,
 	contact_point varchar(100) NOT NULL,
 	artifacts_details varchar(100) NOT NULL,
  	tools_and_platform varchar(100) NOT NULL,
  	used_in_year varchar(100) NOT NULL,
	feature_reference int NOT NULL,
	CONSTRAINT PK_project_project_name PRIMARY KEY(project_name),
	CONSTRAINT feature_FK_feature_reference FOREIGN KEY(feature_reference) REFERENCES
    feature(feature_reference)
);
 -- NEW Version --
create table project (
	project_name VARCHAR(100) UNIQUE NOT NULL,
	regionUS bool NOT NULL,
 	regionCA bool NOT NULL,
	regionEU bool NOT NULL,
 	regionAUNZ bool NOT NULL,
 	regionChina bool NOT NULL,
 	sector VARCHAR(100) NOT NULL,
 	project_contact_point VARCHAR(100) NOT NULL,
 	multi_brand bool NOT NULL,
 	brandnames VARCHAR(100) NOT NULL,
 	multi_site bool NOT NULL,
	project_notes VARCHAR(100) NOT NULL,
	last_served_year VARCHAR(100) NOT NULL,
 	client_base VARCHAR(100) NOT NULL,
 	practice VARCHAR(100) NOT NULL,
 	domain VARCHAR(100) NOT NULL,
 	CONSTRAINT PK_project_project_name PRIMARY KEY(project_name)
);

create table sector(
	sector_name VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK_sector_sector_name PRIMARY KEY(sector_name)
);

create table feature (
	feature_ref serial UNIQUE NOT NULL,
	project_name VARCHAR(100) NOT NULL,
	category VARCHAR(100) NOT NULL,
	sub_category VARCHAR(100) NOT NULL,
	feature_name VARCHAR(100) NOT NULL,
	description VARCHAR(100) NOT NULL,
	type VARCHAR(100) NOT NULL,
	poc VARCHAR(100) NOT NULL,
	artifact_detail VARCHAR(100) NOT NULL,
	used_year VARCHAR(100) NOT NULL,
	feature_extended bool NOT NULL,
	alternate_POC VARCHAR(100) NOT NULL,
	CONSTRAINT PK_feature_feature_ref PRIMARY KEY(feature_ref),
	CONSTRAINT FK_project_name FOREIGN KEY(project_name) REFERENCES
    project(project_name)
);
ALTER SEQUENCE feature_feature_ref_seq RESTART WITH 100;

create table userdata (
	user_id serial UNIQUE NOT NULL,
	sapient_id int UNIQUE NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK_user_user_id PRIMARY KEY(user_id)
);
ALTER SEQUENCE userdata_user_id_seq RESTART WITH 100;

create table feature_project(
	id serial UNIQUE NOT NULL, 
	project_name VARCHAR(100) NOT NULL,
	poc VARCHAR(100) NOT NULL,
	artifact_detail VARCHAR(100) NOT NULL,
	feature_ref INTEGER NOT NULL,
	used_year VARCHAR(100) NOT NULL,
	feature_extended bool NOT NULL,
	CONSTRAINT PK_feature_project_id PRIMARY KEY(id),
	CONSTRAINT feature_project_FK_project_name FOREIGN KEY(project_name) REFERENCES project(project_name),
	CONSTRAINT feature_project_FK_feature_ref FOREIGN KEY(feature_ref) REFERENCES feature(feature_ref)
);
ALTER SEQUENCE feature_project_id_seq RESTART WITH 100;
