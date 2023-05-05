package com.dataentry.api.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			List<Object> paramList = jsonFeature.createParamsList();
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
					list.add(jsonFeature.getProject_name());
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
								Feature feature = new Feature(resultSetJoinedTables.getInt(1),
										resultSetJoinedTables.getString(2), resultSetJoinedTables.getString(3),
										resultSetJoinedTables.getString(4), resultSetJoinedTables.getString(5),
										resultSetJoinedTables.getString(6), resultSetJoinedTables.getString(7),
										resultSetJoinedTables.getString(8), resultSetJoinedTables.getString(9),
										resultSetJoinedTables.getString(10), resultSetJoinedTables.getBoolean(11),
										resultSetJoinedTables.getString(12));
								Project project = new Project(resultSetJoinedTables.getString(13),
										resultSetJoinedTables.getBoolean(14), resultSetJoinedTables.getBoolean(15),
										resultSetJoinedTables.getBoolean(16), resultSetJoinedTables.getBoolean(17),
										resultSetJoinedTables.getBoolean(18), resultSetJoinedTables.getString(19),
										resultSetJoinedTables.getString(20), resultSetJoinedTables.getBoolean(21),
										resultSetJoinedTables.getString(22), resultSetJoinedTables.getBoolean(23),
										resultSetJoinedTables.getString(24), resultSetJoinedTables.getString(25),
										resultSetJoinedTables.getString(26), resultSetJoinedTables.getString(27),
										resultSetJoinedTables.getString(28));
								Feature_Project feature_Project = new Feature_Project(project, feature);
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
		List<Feature_Project> list = new ArrayList<>();
		DatabaseMgr databaseMgr = new DatabaseMgr();
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				ResultSet resultSet = databaseMgr.getDQLResultSet(
						resourceHelpers.getResource("select.featureproject.table", "dbQueries.properties"), connection);

				while (resultSet != null && resultSet.next()) {
					Feature_Project feature_Project = new Feature_Project(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6),
							resultSet.getBoolean(7));
					list.add(feature_Project);
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
		List<Feature_Project> datalist = new ArrayList<>();
		for (int i = 0; i < 19; i++) {
			paramlist.add(keyword);
		}
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				ResultSet resultSet = databaseMgr.getDQLResultSetWithParameter(
						resourceHelpers.getResource("search.featureproject", "dbQueries.properties"), paramlist,
						connection);
				while (resultSet != null && resultSet.next()) {
					Feature_Project feature_Project = new Feature_Project(resultSet.getInt(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6),
							resultSet.getBoolean(7));
					datalist.add(feature_Project);
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
}
