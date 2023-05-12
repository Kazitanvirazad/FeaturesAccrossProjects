
-- select feature.* from feature order by feature.feature_reference desc limit 1;
-- select feature.* from feature;
-- insert into project (project_name,contact_point,artifacts_details,tools_and_platform,used_in_year,feature_reference) 
-- values ('Jenny Craig','PS Sales','Web App','SFCC','2023',101);

--  insert into feature (practice,domain,sector,category,sub_category,feature_short_name,feature_description) values 
-- ('practice data','domain data','sector data','category data','sub_category data','feature_short_name data','feature_description data');

-- select p.project_name,p.contact_point,p.artifacts_details,p.tools_and_platform,p.used_in_year,p.feature_reference,f.practice,f.domain,f.sector,f.category,f.sub_category,f.feature_short_name,f.feature_description from project p, feature f where p.feature_reference = f.feature_reference and f.feature_reference = 108;
-- select p.project_name,p.contact_point,p.artifacts_details,p.tools_and_platform,p.used_in_year,p.feature_reference,f.practice,f.domain,f.sector,f.category,f.sub_category,f.feature_short_name,f.feature_description from project p, feature f where p.feature_reference = f.feature_reference;


-- ALTER SEQUENCE feature_feature_ref_seq RESTART WITH 100;
-- ALTER TABLE feature ADD CONSTRAINT FK_project_name FOREIGN KEY (project_name) REFERENCES project(project_name);


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


--  select feature_project.* from feature_project;
--  select feature.* from feature;
--  select userdata.* from userdata;
-- select project.* from project;
-- insert into feature_project (project_name,poc,artifact_detail,feature_ref,used_year, feature_extended)
-- values ('Jenny Craig','Anuj Jain','Good Artifact',100,'2923', true);
-- delete from feature where project_name ='Tronz TWS';
-- select f.*, p.* from project p, feature f where p.project_name = f.project_name and f.project_name = 'Jenny Craig';
-- ALTER TABLE feature DROP CONSTRAINT UNIQUE project_name RESTRICT;
-- ALTER TABLE feature ADD CONSTRAINT FK_project_name FOREIGN KEY (project_name) REFERENCES project(project_name) MATCH FULL;
-- ALTER TABLE feature DROP UNIQUE (project_name);
-- ALTER TABLE feature ADD CONSTRAINT unique_project_name UNIQUE (project_name);
-- ALTER TABLE feature DROP CONSTRAINT unique_project_name;
-- DROP TABLE feature CASCADE;
-- DROP TABLE userdata CASCADE;

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


-- SELECT f.category, f.sub_category, f.feature_name, f.type, fp.project_name, fp.poc, fp.feature_extended, f.alternate_POC, p.project_name, p.sector, p.client_base, p.domain, p.multi_brand, p.multi_site, p.last_served_year FROM feature_project fp, project p, feature f where f.feature_ref = fp.feature_ref and p.project_name = fp.project_name and (f.project_name ilike 'Jenny Craig' or f.category ilike 'Jenny Craig' or f.sub_category ilike 'Jenny Craig' or f.feature_name ilike 'Jenny Craig' or f.description ilike 'Jenny Craig' or f.type ilike 'Jenny Craig' or f.poc ilike 'Jenny Craig' or f.artifact_detail ilike 'Jenny Craig' or f.used_year ilike 'Jenny Craig' or f.alternate_POC ilike 'Jenny Craig' or p.sector ilike 'Jenny Craig' or p.project_contact_point ilike 'Jenny Craig' or p.brandnames ilike 'Jenny Craig' or p.project_notes ilike 'Jenny Craig' or p.last_served_year ilike 'Jenny Craig' or p.client_base ilike 'Jenny Craig' or p.practice ilike 'Jenny Craig' or p.domain ilike 'Jenny Craig');

-- select fp.* from feature_project fp, feature f,  project p where p.project_name = f.project_name and fp.project_name=f.project_name and fp.feature_ref = f.feature_ref and (f.project_name ilike 'Kazi Tanvir Azad' or f.category ilike 'Kazi Tanvir Azad' or f.sub_category ilike 'Kazi Tanvir Azad' or f.sub_category ilike 'Kazi Tanvir Azad' or f.feature_name ilike 'Kazi Tanvir Azad' or f.description ilike 'Kazi Tanvir Azad' or f.type ilike 'Kazi Tanvir Azad' or f.poc ilike 'Kazi Tanvir Azad' or f.artifact_detail ilike 'Kazi Tanvir Azad' or f.used_year ilike 'Kazi Tanvir Azad' or f.alternate_POC ilike 'Kazi Tanvir Azad' or p.sector ilike 'Kazi Tanvir Azad' or p.project_contact_point ilike 'Kazi Tanvir Azad' or  p.brandnames ilike 'Kazi Tanvir Azad' or p.project_notes ilike 'Kazi Tanvir Azad' or p.last_served_year ilike 'Kazi Tanvir Azad' or p.client_base ilike 'Kazi Tanvir Azad' or p.practice ilike 'Kazi Tanvir Azad' or p.domain ilike 'Kazi Tanvir Azad');

-- SELECT f.category, f.sub_category, f.feature_name, f.type, fp.project_name, fp.poc, fp.feature_extended,
-- f.alternate_POC, p.project_name, p.sector, p.client_base, p.domain, p.multi_brand, p.multi_site, 
-- p.last_served_year FROM feature_project fp, project p, feature f where f.feature_ref = fp.feature_ref and
--  p.project_name = fp.project_name;


-- select feature.* from feature; -- where project_name='Tronz TWS';
-- LEFT OUTER JOIN feature F ON F.feature_ref= fp.feature_ref  
-- LEFT OUTER JOIN project P on P.project_name = fp.project_name and P.project_name ='Jenny Craig';





-- select project.* from project where prproject_name='Test1oject_name='Test1';
delete from project where project_name='Test';
-- insert into sector (sector_name) values ('Retail'), ('Healthcare'), ('FMCG');




