package com.dataentry.api.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.postgresql.util.PSQLException;

import com.dataentry.api.dao.Feature;
import com.dataentry.api.dao.Feature_Project;
import com.dataentry.api.dao.Project;
import com.dataentry.api.response.ResponseData;
import com.dataentry.database.helpers.ORM_DBConnector;
import com.dataentry.database.utils.DatabaseMgr;
import com.google.gson.Gson;

public class DatabaseHelpers {
	private static ResourceHelpers resourceHelpers = new ResourceHelpers();

	public String getJSONTxt(HttpServletRequest request) {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			ServletInputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (Exception e) {
		}
		body = stringBuilder.toString();
		return body;
	}

	public ResponseData addProject(String jsonTxt) {
		Gson gson = new Gson();
		DatabaseMgr databaseMgr = new DatabaseMgr();
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		ResponseData responseData = new ResponseData();
		Project jsonProject = gson.fromJson(jsonTxt, Project.class);
		boolean isProjectAdded = false;
		if (jsonProject == null || !(jsonProject instanceof Project)) {
			responseData.setError(true);
			responseData.setMessage("Incorrect or invalid input");
		} else {
			List<Object> paramList = jsonProject.createParamsList();
			String query = resourceHelpers.getResource("insert.project.table", "dbQueries.properties");
			try {
				connection = orm_DBConnector.getConnection();
				if (connection != null) {
					List<Object> list = new ArrayList<>();
					list.add(jsonProject.getProject_name());
					ResultSet resultSet = databaseMgr.getDQLResultSetWithParameter(
							resourceHelpers.getResource("select.checkproject.exist", "dbQueries.properties"), list,
							connection);
					if (resultSet != null && resultSet.next()) {
						responseData.setError(true);
						responseData.setMessage("Project already exists");
					} else {
						isProjectAdded = databaseMgr.executeDML(query, paramList, connection);
						if (!isProjectAdded) {
							responseData.setError(true);
							responseData.setMessage("Failed to add Project");
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		if (connection == null) {
			responseData.setError(true);
			responseData.setMessage("Connection to database server failed");
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (isProjectAdded) {
			responseData.setError(false);
			responseData.setMessage("Project added successfully");
		}
		return responseData;
	}

	public ResponseData addFeature(String jsonTxt) {
		Gson gson = new Gson();
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		ResponseData responseData = new ResponseData();
		Connection connection = null;
		Feature jsonFeature = gson.fromJson(jsonTxt, Feature.class);
		boolean isFeatureAdded = false;
		boolean isFeatureProjectAdded = false;
		boolean isConnectionExist = false;
		if (jsonFeature == null || !(jsonFeature instanceof Feature)) {
			responseData.setError(true);
			responseData.setMessage("Incorrect or invalid input");
		} else {
			Feature feature1 = new Feature(jsonFeature);
			List<Object> paramList = feature1.createParamsList();
			String query = resourceHelpers.getResource("insert.feature.table", "dbQueries.properties");
			try {
				connection = orm_DBConnector.getConnection();
				if (connection != null) {
					isConnectionExist = true;
				}
				if (isConnectionExist) {
					connection.setAutoCommit(false);
					DatabaseMgr databaseMgr = new DatabaseMgr();
					List<Object> list = new ArrayList<>();
					list.add(feature1.getProject_name());
					ResultSet resultSet = databaseMgr.getDQLResultSetWithParameter(
							resourceHelpers.getResource("select.checkproject.exist", "dbQueries.properties"), list,
							connection);
					if (resultSet != null && resultSet.next()) {
						isFeatureAdded = databaseMgr.executeMultiTableDML(query, paramList, connection);
						if (!isFeatureAdded) {
							connection.rollback();
							responseData.setError(true);
							responseData.setMessage("Failed to add Feature");
						} else {
							connection.commit();
							String joinedquery = resourceHelpers.getResource("jointable.feature.project",
									"dbQueries.properties");
							ResultSet resultSetJoinedTables = databaseMgr.getDQLResultSetWithParameter(joinedquery,
									list, connection);
							while (resultSetJoinedTables != null && resultSetJoinedTables.next()) {
								Project project = new Project(resultSetJoinedTables.getString(1),
										resultSetJoinedTables.getBoolean(2), resultSetJoinedTables.getBoolean(3),
										resultSetJoinedTables.getBoolean(4), resultSetJoinedTables.getBoolean(5),
										resultSetJoinedTables.getBoolean(6), resultSetJoinedTables.getString(7),
										resultSetJoinedTables.getString(8), resultSetJoinedTables.getBoolean(9),
										resultSetJoinedTables.getString(10), resultSetJoinedTables.getBoolean(11),
										resultSetJoinedTables.getString(12), resultSetJoinedTables.getString(13),
										resultSetJoinedTables.getString(14), resultSetJoinedTables.getString(15),
										resultSetJoinedTables.getString(16));
								Feature_Project feature_Project = new Feature_Project(project, feature1);
								paramList = feature_Project.createParamsList();
								isFeatureProjectAdded = databaseMgr.executeMultiTableDML(resourceHelpers.getResource(
										"insert.featureproject.table", "dbQueries.properties"), paramList, connection);
								if (isFeatureProjectAdded) {
									connection.commit();
								} else {
									connection.rollback();
									responseData.setError(true);
									responseData.setMessage("Failed to add Feature Project");
									break;
								}
							}
						}
					} else {
						responseData.setError(true);
						responseData.setMessage("Project does not exists, please add project from Add Project page");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!isConnectionExist) {
			return new ResponseData(true, "Connection to database server failed");
		}
		if (isFeatureAdded && isFeatureProjectAdded)

		{
			return new ResponseData(false, "Feature added successfully");
		}
		return responseData;
	}

	public ResponseData getFeatureList() {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		List<Object> list = new ArrayList<>();
		DatabaseMgr databaseMgr = new DatabaseMgr();
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				ResultSet resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.featureproject.data", "dbQueries.properties"), connection);
				if (resultSet != null) {
					list = getFeatureProjectData(resultSet);
				}
			}
		} catch (SQLException | URISyntaxException e) {
			e.printStackTrace();
		}
		if (connection == null) {
			return new ResponseData(true, "Connection to database server failed");
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (list.size() > 0) {
			return new ResponseData(false, null, list);
		} else {
			return new ResponseData(true, "No data found");
		}
	}

	public ResponseData getSearchData(String keyword) {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		DatabaseMgr databaseMgr = new DatabaseMgr();
		List<Object> paramlist = new ArrayList<>();
		List<Object> datalist = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			paramlist.add(keyword);
		}
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				ResultSet resultSet = databaseMgr.getDQLResultSetWithParameter(
						resourceHelpers.getResource("search.featureproject.data", "dbQueries.properties"), paramlist,
						connection);
				if (resultSet != null) {
					datalist = getFeatureProjectData(resultSet);
				}
			}
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (connection == null) {
			return new ResponseData(true, "Connection to database server failed");
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (datalist.size() > 0) {
			return new ResponseData(false, datalist);
		} else {
			return new ResponseData(true, "No Features found");
		}
	}

	public ResponseData getProjectList() {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		DatabaseMgr databaseMgr = new DatabaseMgr();
		List<String> project_name_list = new ArrayList<>();
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				ResultSet resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.project.project_name", "dbQueries.properties"), connection);
				while (resultSet != null && resultSet.next()) {
					project_name_list.add(resultSet.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (connection == null) {
			return new ResponseData(true, "Connection to database server failed");
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (project_name_list.size() > 0) {
			return new ResponseData(false, project_name_list);
		} else {
			return new ResponseData(true, "Projects not found");
		}
	}

	public ResponseData getDropdownData() {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		DatabaseMgr databaseMgr = new DatabaseMgr();
		Map<String, List> map = new HashMap<>();

		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				ResultSet resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.sector.sector_name", "dbQueries.properties"), connection);
				List<String> sector_list = new ArrayList<>();
				while (resultSet != null && resultSet.next()) {
					sector_list.add(resultSet.getString(1));
				}
				map.put("sector", sector_list);

				resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.practice.practice_name", "dbQueries.properties"),
						connection);
				List<String> practice_list = new ArrayList<>();
				while (resultSet != null && resultSet.next()) {
					practice_list.add(resultSet.getString(1));
				}
				map.put("practice", practice_list);

				resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.domain.domain_name", "dbQueries.properties"), connection);
				List<String> domain_list = new ArrayList<>();
				while (resultSet != null && resultSet.next()) {
					domain_list.add(resultSet.getString(1));
				}
				map.put("domain", domain_list);

				resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.client_lead.cient_type", "dbQueries.properties"),
						connection);
				List<String> client_lead_list = new ArrayList<>();
				while (resultSet != null && resultSet.next()) {
					client_lead_list.add(resultSet.getString(1));
				}
				map.put("client_lead", client_lead_list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (connection == null) {
			return new ResponseData(true, "Connection to database server failed");
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (map.size() > 0) {
			return new ResponseData(false, map);
		} else {
			return new ResponseData(true, "Sectors not found");
		}
	}

	private List<Object> getFeatureProjectData(ResultSet resultset) {
		List<Object> list = new ArrayList<>();
		try {
			while (resultset != null && resultset.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("category", resultset.getString(1));
				map.put("sub_category", resultset.getString(2));
				map.put("feature_name", resultset.getString(3));
				map.put("type", resultset.getString(4));
				map.put("project_name", resultset.getString(5));
				map.put("poc", resultset.getString(6));
				map.put("feature_extended", resultset.getBoolean(7));
				map.put("alternate_POC", resultset.getString(8));
				map.put("project_name", resultset.getString(9));
				map.put("sector", resultset.getString(10));
				map.put("client_base", resultset.getString(11));
				map.put("domain", resultset.getString(12));
				map.put("multi_brand", resultset.getBoolean(13));
				map.put("multi_site", resultset.getBoolean(14));
				map.put("last_served_year", resultset.getString(15));
				map.put("artifact_detail", resultset.getString(16));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
