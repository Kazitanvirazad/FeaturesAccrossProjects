package com.dataentry.api.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.PSQLException;

import com.dataentry.api.dao.Project;
import com.dataentry.api.helpers.DBHelpers;
import com.dataentry.api.response.ResponseData;
import com.google.gson.Gson;

public class AddData extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBHelpers dbHelpers = new DBHelpers();
		Project project = null;
		project = dbHelpers.addData(request);

		ResponseData responseData = null;
		Gson gson = new Gson();
		if (project != null) {
			responseData = new ResponseData(false, "", project);
			response.setStatus(201);
		} else {
			responseData = new ResponseData(true, "Error Occurred: Data not added", null);
			response.setStatus(409);
		}
		String x = gson.toJson(responseData);
		response.getWriter().write(x);

	}

}
