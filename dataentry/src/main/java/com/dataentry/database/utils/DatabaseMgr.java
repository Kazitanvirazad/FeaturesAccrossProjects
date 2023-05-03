package com.dataentry.database.utils;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.dataentry.database.helpers.*;

public class DatabaseMgr {

	public ResultSet getDQLResultSet(String query) throws SQLException {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			return resultSet;
		} else {
			return null;
		}
	}

	public ResultSet getDQLResultSetWithParameter(String query, Object param, Integer count)
			throws SQLException, PSQLException {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				statement = connection.prepareStatement(query);
				if (count != null) {
					for (int i = 1; i <= count; i++) {
						statement.setObject(i, param);
					}
				} else {
					statement.setObject(1, param);
				}
				resultSet = statement.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			return resultSet;
		} else {
			return null;
		}
	}

	public boolean executeDML(String query, List<Object> paramList) throws SQLException {
		ORM_DBConnector orm_DBConnector = new ORM_DBConnector();
		Connection connection = null;
		PreparedStatement statement = null;
		boolean flag = false;
		try {
			connection = orm_DBConnector.getConnection();
			if (connection != null) {
				connection.setAutoCommit(false);
				statement = connection.prepareStatement(query);
				int count = 1;
				for (Object param : paramList) {
					statement.setObject(count, param);
					count++;
				}
				int result = statement.executeUpdate();
				if (result > 0) {
					flag = true;
					connection.commit();
				}
			}
		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback();
			}
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}
}
