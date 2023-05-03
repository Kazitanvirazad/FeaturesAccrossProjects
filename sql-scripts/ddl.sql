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