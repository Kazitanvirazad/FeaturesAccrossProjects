package com.dataentry.api.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.postgresql.util.PSQLException;

import com.dataentry.api.dao.Feature;
import com.dataentry.api.dao.Project;
import com.dataentry.database.helpers.ORM_DBConnector;
import com.dataentry.database.utils.DatabaseMgr;
import com.google.gson.Gson;

public class DBHelpers {
	private Gson gson = new Gson();
	private ResourceHelpers resourceHelpers = new ResourceHelpers();

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
			// TODO: handle exception
		}
		body = stringBuilder.toString();
		return body;
	}

	public List<Project> getListData() {
		DatabaseMgr databaseMgr = new DatabaseMgr();
		List<Project> projectList = new ArrayList<>();
		try {
			ResultSet resultSet = databaseMgr
					.getDQLResultSet(resourceHelpers.getResource("select.getlistdata", "dbQueries.properties"));
			while (resultSet != null && resultSet.next()) {
				Feature featureRes = new Feature(resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8),
						resultSet.getString(9), resultSet.getString(10), resultSet.getString(11),
						resultSet.getString(12), resultSet.getString(13));
				Project projectRes = new Project(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), featureRes);
				projectList.add(projectRes);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectList;
	}

	public Project addData(HttpServletRequest request) {
		DatabaseMgr databaseMgr = new DatabaseMgr();
		String body = getJSONTxt(request);
		Project project = gson.fromJson(body, Project.class);
		Project projectRes = null;
		Feature feature = null;
		boolean isDataInserted = false;
		Integer feature_reference = null;
		List<Object> paramList = new ArrayList<>();

		Feature reqFeature = new Feature(project.getFeature().getPractice(), project.getFeature().getDomain(),
				project.getFeature().getSector(), project.getFeature().getCategory(),
				project.getFeature().getSub_category(), project.getFeature().getFeature_short_name());
		paramList.add(reqFeature.getPractice());
		paramList.add(reqFeature.getDomain());
		paramList.add(reqFeature.getSector());
		paramList.add(reqFeature.getCategory());
		paramList.add(reqFeature.getSub_category());
		paramList.add(reqFeature.getFeature_short_name());
		paramList.add(reqFeature.getFeature_description());
		boolean isProjectExist = false;
		ResultSet result;
		try {
			result = databaseMgr.getDQLResultSetWithParameter(
					resourceHelpers.getResource("select.checkproject.exist", "dbQueries.properties"),
					project.getProject_name(), null);
			if (result != null && result.next()) {
				isProjectExist = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!isProjectExist) {
			try {
				isDataInserted = databaseMgr.executeDML(
						resourceHelpers.getResource("insert.feature.table", "dbQueries.properties"), paramList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isDataInserted) {
				ResultSet resultSet = null;
				try {
					resultSet = databaseMgr.getDQLResultSet(
							resourceHelpers.getResource("select.feature.table.lastrow", "dbQueries.properties"));
					if (resultSet != null) {
						while (resultSet.next()) {
							feature = new Feature(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
									resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
									resultSet.getString(7), resultSet.getString(8));
							break;
						}
						if (feature != null) {
							paramList.clear();
							paramList.add(project.getProject_name());
							paramList.add(project.getContact_point());
							paramList.add(project.getArtifacts_details());
							paramList.add(project.getTools_and_platform());
							paramList.add(project.getUsed_in_year());
							paramList.add(feature.getFeature_reference());
							isDataInserted = databaseMgr.executeDML(
									resourceHelpers.getResource("insert.project.table", "dbQueries.properties"),
									paramList);
							if (isDataInserted) {
								resultSet = databaseMgr.getDQLResultSetWithParameter(resourceHelpers
										.getResource("select.join.featureproject", "dbQueries.properties"),
										feature.getFeature_reference(), null);
								while (resultSet != null && resultSet.next()) {
									feature = new Feature(resultSet.getInt(6), resultSet.getString(7),
											resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
											resultSet.getString(11), resultSet.getString(12), resultSet.getString(13));
									projectRes = new Project(resultSet.getString(1), resultSet.getString(2),
											resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
											resultSet.getInt(6), feature);
									break;
								}
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return projectRes;
	}

	public List<Project> getProjectDataFromSearchKey(Object key) {
		DatabaseMgr databaseMgr = new DatabaseMgr();
		Project projectRes = null;
		Feature feature = null;
		List<Project> list = new ArrayList<>();
		String queryString = resourceHelpers.getResource("select.searchdatakeyword", "dbQueries.properties");
		ResultSet resultSet = null;
		try {
			resultSet = databaseMgr.getDQLResultSetWithParameter(queryString, key, 12);
			while (resultSet != null && resultSet.next()) {
				feature = new Feature(resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8),
						resultSet.getString(9), resultSet.getString(10), resultSet.getString(11),
						resultSet.getString(12), resultSet.getString(13));
				projectRes = new Project(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), feature);
				list.add(projectRes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
