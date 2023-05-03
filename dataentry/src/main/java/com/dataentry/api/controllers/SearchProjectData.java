package com.dataentry.api.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.dao.Project;
import com.dataentry.api.helpers.DBHelpers;
import com.dataentry.api.response.ResponseData;
import com.google.gson.Gson;

public class SearchProjectData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBHelpers dbHelpers = new DBHelpers();
		Gson gson = new Gson();
		Object key = request.getParameter("keyword");
		ResponseData responseData = null;
		List<Project> list = new ArrayList<>();
		if (key != null) {
			list = dbHelpers.getProjectDataFromSearchKey(key);
			if (list.size() == 0) {
				responseData = new ResponseData(true, "Projects not found", null);
				response.setStatus(404);
			} else {
				responseData = new ResponseData(false, null, list);
				response.setStatus(200);
			}
		} else {
			responseData = new ResponseData(true, "Invalid or Empty search key", null);
			response.setStatus(400);
		}
		response.getWriter().write(gson.toJson(responseData));
	}

}
