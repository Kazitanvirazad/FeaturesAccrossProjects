package com.dataentry.database.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

public class DatabaseMgr {

	public ResultSet getDQLResultSet(String query, Connection connection) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			if (connection != null) {
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultSet != null) {
			return resultSet;
		} else {
			return null;
		}
	}

	public ResultSet getDQLResultSetWithParameter(String query, List<Object> paramList, Connection connection)
			throws SQLException, PSQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			if (connection != null) {
				statement = connection.prepareStatement(query);
				int count = 1;
				for (Object param : paramList) {
					statement.setObject(count, param);
					count++;
				}
				resultSet = statement.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultSet != null) {
			return resultSet;
		} else {
			return null;
		}
	}

	public boolean executeDML(String query, List<Object> paramList, Connection connection) throws SQLException {
		PreparedStatement statement = null;
		boolean flag = false;
		try {
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
		}

		return flag;
	}

	public boolean executeMultiTableDML(String query, List<Object> paramList, Connection connection)
			throws SQLException {
		PreparedStatement statement = null;
		boolean flag = false;
		try {
			if (connection != null) {
				statement = connection.prepareStatement(query);
				int count = 1;
				for (Object param : paramList) {
					statement.setObject(count, param);
					count++;
				}
				int result = statement.executeUpdate();
				if (result > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
