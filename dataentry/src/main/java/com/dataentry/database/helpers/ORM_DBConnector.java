package com.dataentry.database.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.net.URISyntaxException;

public class ORM_DBConnector {

	public Connection getConnection() throws URISyntaxException, SQLException {
		InitialContext cxt;
		DataSource ds;
		Connection connection = null;

		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
