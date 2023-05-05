-- create table test(
-- 	name varchar,
-- 	city varchar
-- );
-- insert into test (name,city) values ('kazi','bangalore'), ('fahim','berhampore');
-- select * from test;
-- select 1;
-- create table feature(
--  	feature_reference serial UNIQUE NOT NULL,
--  	practice varchar(100) NOT NULL,
-- 	domain varchar(100) NOT NULL,
--  	sector varchar(100) NOT NULL,
--  	category varchar(100) NOT NULL,
--  	sub_category varchar(100) NOT NULL,
--  	feature_short_name varchar(100) NOT NULL,
--  	feature_description varchar(300) NOT NULL,
--  	CONSTRAINT PK_feature_feature_reference PRIMARY KEY(feature_reference)
-- );
-- ALTER SEQUENCE feature_feature_reference_seq RESTART WITH 100;

-- create table project(
--  	project_name VARCHAR(100) UNIQUE NOT NULL,
--  	contact_point varchar(100) NOT NULL,
--  	artifacts_details varchar(100) NOT NULL,
--   	tools_and_platform varchar(100) NOT NULL,
--   	used_in_year varchar(100) NOT NULL,
-- 	feature_reference int NOT NULL,
-- 	CONSTRAINT PK_project_project_name PRIMARY KEY(project_name),
-- 	CONSTRAINT feature_FK_feature_reference FOREIGN KEY(feature_reference) REFERENCES
--     feature(feature_reference)
-- );
-- select feature.* from feature order by feature.feature_reference desc limit 1;
-- select feature.* from feature;
-- insert into project (project_name,contact_point,artifacts_details,tools_and_platform,used_in_year,feature_reference) 
-- values ('Jenny Craig','PS Sales','Web App','SFCC','2023',101);

--  insert into feature (practice,domain,sector,category,sub_category,feature_short_name,feature_description) values 
-- ('practice data','domain data','sector data','category data','sub_category data','feature_short_name data','feature_description data');

-- select p.project_name,p.contact_point,p.artifacts_details,p.tools_and_platform,p.used_in_year,p.feature_reference,f.practice,f.domain,f.sector,f.category,f.sub_category,f.feature_short_name,f.feature_description from project p, feature f where p.feature_reference = f.feature_reference and f.feature_reference = 108;
-- select p.project_name,p.contact_point,p.artifacts_details,p.tools_and_platform,p.used_in_year,p.feature_reference,f.practice,f.domain,f.sector,f.category,f.sub_category,f.feature_short_name,f.feature_description from project p, feature f where p.feature_reference = f.feature_reference;

-- create table sector(
-- 	sector_name VARCHAR(100) UNIQUE NOT NULL,
-- 	CONSTRAINT PK_sector_sector_name PRIMARY KEY(sector_name)
-- );

-- create table project (
-- 	project_name VARCHAR(100) UNIQUE NOT NULL,
-- 	regionUS bool NOT NULL,
--  	regionCA bool NOT NULL,
-- 	regionEU bool NOT NULL,
--  	regionAUNZ bool NOT NULL,
--  	regionChina bool NOT NULL,
--  	sector VARCHAR(100) NOT NULL,
--  	project_contact_point VARCHAR(100) NOT NULL,
--  	multi_brand bool NOT NULL,
--  	brandames VARCHAR(100) NOT NULL,
--  	multi_site bool NOT NULL,
-- 	roject_notes VARCHAR(100) NOT NULL,
-- 	ast_served_year VARCHAR(100) NOT NULL,
--  	client_base VARCHAR(100) NOT NULL,
--  	practice VARCHAR(100) NOT NULL,
--  	domain VARCHAR(100) NOT NULL,
--  	CONSTRAINT PK_project_project_name PRIMARY KEY(project_name)
-- );

-- create table feature (
-- 	feature_ref serial UNIQUE NOT NULL,
-- 	project_name VARCHAR(100) UNIQUE NOT NULL,
-- 	category VARCHAR(100) NOT NULL,
-- 	sub_category VARCHAR(100) NOT NULL,
-- 	feature_name VARCHAR(100) NOT NULL,
-- 	description VARCHAR(100) NOT NULL,
-- 	type VARCHAR(100) NOT NULL,
-- 	poc VARCHAR(100) NOT NULL,
-- 	artifact_detail VARCHAR(100) NOT NULL,
-- 	used_year VARCHAR(100) NOT NULL,
-- 	feature_extended VARCHAR(100) NOT NULL,
-- 	alternate_POC VARCHAR(100) NOT NULL,
-- 	CONSTRAINT PK_feature_feature_ref PRIMARY KEY(feature_ref),
-- 	CONSTRAINT feature_FK_project_name FOREIGN KEY(project_name) REFERENCES
--     project(project_name)
-- );
-- ALTER SEQUENCE feature_feature_ref_seq RESTART WITH 100;
-- ALTER TABLE feature ADD CONSTRAINT FK_project_name FOREIGN KEY (project_name) REFERENCES project(project_name);

-- create table userdata (
-- 	user_id serial UNIQUE NOT NULL,
-- 	sapient_id int UNIQUE NOT NULL,
-- 	first_name VARCHAR(100) NOT NULL,
-- 	last_name VARCHAR(100) NOT NULL,
-- 	email VARCHAR(100) UNIQUE NOT NULL,
-- 	CONSTRAINT PK_user_user_id PRIMARY KEY(user_id)
-- );

-- select project.* from project where project_name='Jenny Craig';

--  insert into project (project_name, regionUS, regionCA, regionEU, regionAUNZ, regionChina,sector,
--   					 project_contact_point, multi_brand, brandames, multi_site, project_notes,
--   					 last_served_year, client_base, practice, domain) values 
--   					 ('Jenny Craig', true, true, false, true, false, 'Retail', 'Anuj Jain', false,
--   					 'Concord, Ebel,  Movado', true, 'us and ca served by single site',
--   					 '2023', 'External', 'SFCC', 'ECommerce');

--alter table project rename column brandames to brandnames;

-- ALTER TABLE feature ALTER COLUMN feature_extended TYPE boolean USING feature_extended::boolean;

-- insert into feature (project_name,category,sub_category,feature_name,description,type,
-- 					poc,artifact_detail,used_year,feature_extended,alternate_POC) values
-- 					('Asda', 'SMS','Integration','Attentive','Good feature',
-- 					'Custom Integration','Anuj Jain','Good Artifact','2019',true,'Kazi tanvir azad');

-- create table feature_project(
-- 	id serial UNIQUE NOT NULL, 
-- 	project_name VARCHAR(100) NOT NULL,
-- 	poc VARCHAR(100) NOT NULL,
-- 	artifact_detail VARCHAR(100) NOT NULL,
-- 	feature_ref INTEGER NOT NULL,
-- 	used_year VARCHAR(100) NOT NULL,
-- 	feature_extended bool NOT NULL,
-- 	CONSTRAINT PK_feature_project_id PRIMARY KEY(id),
-- 	CONSTRAINT feature_project_FK_project_name FOREIGN KEY(project_name) REFERENCES project(project_name),
-- 	CONSTRAINT feature_project_FK_feature_ref FOREIGN KEY(feature_ref) REFERENCES feature(feature_ref)
-- );
-- select feature_project.* from feature_project;
--  select feature.* from feature;
--  select userdata.* from userdata;

-- select project.project_name from project;
-- insert into feature_project (project_name,poc,artifact_detail,feature_ref,used_year, feature_extended)
-- values ('Jenny Craig','Anuj Jain','Good Artifact',100,'2923', true);
-- delete from feature where project_name ='Fashion Trends';
-- select f.*, p.* from project p, feature f where p.project_name = f.project_name and f.project_name = 'Jenny Craig';
-- ALTER TABLE feature DROP CONSTRAINT UNIQUE project_name RESTRICT;
-- ALTER TABLE feature ADD CONSTRAINT FK_project_name FOREIGN KEY (project_name) REFERENCES project(project_name) MATCH FULL;
-- ALTER TABLE feature DROP UNIQUE (project_name);
-- ALTER TABLE feature ADD CONSTRAINT unique_project_name UNIQUE (project_name);
-- ALTER TABLE feature DROP CONSTRAINT unique_project_name;
-- DROP TABLE feature CASCADE;
-- DROP TABLE feature_project CASCADE;

-- select fp.* from feature_project fp, feature f,  project p 
-- where p.project_name = f.project_name and fp.project_name=f.project_name and fp.feature_ref = f.feature_ref and
-- (f.project_name ilike ? or f.category ilike ? or f.sub_category ilike ? or
-- f.sub_category ilike ? or f.feature_name ilike ? or f.description ilike ? or
--   f.type ilike ? or f.poc ilike ? or f.artifact_detail ilike ? or f.used_year ilike ? or
--   f.alternate_POC ilike ? or p.sector ilike ? or p.project_contact_point ilike ? or
--   p.brandnames ilike ? or p.project_notes ilike ? or p.last_served_year ilike ? or
--  p.client_base ilike ? or p.practice ilike ? or p.domain ilike ?)

-- select fp.* from feature_project fp, feature f,  project p 
-- where p.project_name = f.project_name and fp.project_name=f.project_name and fp.feature_ref = f.feature_ref and
-- (f.project_name ilike 'Jenny Craig' or f.category ilike 'Jenny Craig' or f.sub_category ilike 'Jenny Craig' or
-- f.sub_category ilike 'Jenny Craig' or f.feature_name ilike 'Jenny Craig' or f.description ilike 'Jenny Craig' or
--  f.type ilike 'Jenny Craig' or f.poc ilike 'Jenny Craig' or f.artifact_detail ilike 'Jenny Craig' or f.used_year ilike 'Jenny Craig' or
--  f.alternate_POC ilike 'Jenny Craig' or p.sector ilike 'Jenny Craig' or p.project_contact_point ilike 'Jenny Craig' or
--  p.brandnames ilike 'Jenny Craig' or p.project_notes ilike 'Jenny Craig' or p.last_served_year ilike 'Jenny Craig' or
--  p.client_base ilike 'Jenny Craig' or p.practice ilike 'Jenny Craig' or p.domain ilike 'Jenny Craig');



