package com.dataentry.api.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dataentry.database.utils.DatabaseMgr;

public class GetTest {
	public String query = "select * from test";

	public ArrayList<TestData> getLists() {
		DatabaseMgr databaseMgr = new DatabaseMgr();
		ResultSet resultSet = null;
		try {
			resultSet = databaseMgr.getDQLResultSet(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<TestData> list = new ArrayList<>();
		try {
			while (resultSet != null && resultSet.next()) {
				String name = resultSet.getString(1);
				String city = resultSet.getString(2);
				TestData test = new TestData(name, city);
				list.add(test);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
