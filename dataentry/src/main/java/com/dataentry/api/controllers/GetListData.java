package com.dataentry.api.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.dao.Project;
import com.dataentry.api.helpers.DBHelpers;
import com.dataentry.api.response.ResponseData;
import com.google.gson.Gson;

public class GetListData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBHelpers dbHelpers = new DBHelpers();
		Gson gson = new Gson();
		ResponseData responseData = null;
		List<Project> projectList = dbHelpers.getListData();
		if (projectList.size() > 0) {
			responseData = new ResponseData(false, "", projectList);
			response.setStatus(200);
		} else {
			responseData = new ResponseData(true, "Error Occurred: Data not found", null);
			response.setStatus(404);
		}
		response.getWriter().write(gson.toJson(responseData));
	}

}
