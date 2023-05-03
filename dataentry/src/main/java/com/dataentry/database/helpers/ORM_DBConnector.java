package com.dataentry.database.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.dataentry.api.helpers.ResourceHelpers;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;

public class ORM_DBConnector {

	private String getDatabaseURL() {
		ResourceHelpers resourceHelpers = new ResourceHelpers();
		String url = resourceHelpers.getResource("url", "dbCredentials.properties");
		return url != null ? url : "";
	}

	public Connection getConnection() throws URISyntaxException, SQLException {
		String url = getDatabaseURL();
		return DriverManager.getConnection(url);
	}
}
