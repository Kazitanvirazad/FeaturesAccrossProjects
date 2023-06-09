create table project (
	project_name VARCHAR(100) UNIQUE NOT NULL,
	regionUS bool NOT NULL,
 	regionCA bool NOT NULL,
	regionEU bool NOT NULL,
 	regionAUNZ bool NOT NULL,
 	regionChina bool NOT NULL,
 	sector VARCHAR(100) NULL,
 	project_contact_point VARCHAR(100) NULL,
 	multi_brand bool NULL,
 	brandnames VARCHAR(100) NULL,
 	multi_site bool NOT NULL,
	project_notes VARCHAR(100) NULL,
	last_served_year VARCHAR(100) NULL,
 	client_base VARCHAR(100) NULL,
 	practice VARCHAR(100) NULL,
 	domain VARCHAR(100) NULL,
 	CONSTRAINT PK_project_project_name PRIMARY KEY(project_name)
);

create table sector(
	sector_name VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK_sector_sector_name PRIMARY KEY(sector_name)
);

create table practice(
	practice_name VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK_practice_practice_name PRIMARY KEY(practice_name)
);

create table client_lead(
	cient_type VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT client_lead_cient_type PRIMARY KEY(cient_type)
);

create table domain(
	domain_name VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK_domain_domain_name PRIMARY KEY(domain_name)
);

create table feature (
	feature_ref VARCHAR(500) UNIQUE NOT NULL,
	project_name VARCHAR(100) NULL,
	category VARCHAR(100) NOT NULL,
	sub_category VARCHAR(100) NULL,
	feature_name VARCHAR(100) NOT NULL,
	description VARCHAR(100) NULL,
	type VARCHAR(100) NOT NULL,
	poc VARCHAR(100) NULL,
	artifact_detail VARCHAR(100) NULL,
	used_year VARCHAR(100) NULL,
	feature_extended bool NOT NULL,
	alternate_POC VARCHAR(100) NULL,
	CONSTRAINT PK_feature_feature_ref PRIMARY KEY(feature_ref),
	CONSTRAINT FK_project_name FOREIGN KEY(project_name) REFERENCES
    project(project_name)
);

create table userdata (
	user_id VARCHAR(500) UNIQUE NOT NULL,
	sapient_id int UNIQUE NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	CONSTRAINT PK_user_user_id PRIMARY KEY(user_id)
);

create table feature_project(
	id VARCHAR(500) UNIQUE NOT NULL, 
	project_name VARCHAR(100) NULL,
	poc VARCHAR(100) NULL,
	artifact_detail VARCHAR(100) NULL,
	feature_ref VARCHAR(500) NULL,
	used_year VARCHAR(100) NULL,
	feature_extended bool NOT NULL,
	CONSTRAINT PK_feature_project_id PRIMARY KEY(id),
	CONSTRAINT feature_project_FK_project_name FOREIGN KEY(project_name) REFERENCES project(project_name),
	CONSTRAINT feature_project_FK_feature_ref FOREIGN KEY(feature_ref) REFERENCES feature(feature_ref)
);

