package com.dataentry.api.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.helpers.DatabaseHelpers;
import com.dataentry.api.response.ResponseData;
import com.google.gson.Gson;

public class AddProject extends HttpServlet {

	private static final long serialVersionUID = 5591034292412379995L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseHelpers databaseHelpers = new DatabaseHelpers();
		Gson gson = new Gson();
		String requestBodyTxt = databaseHelpers.getJSONTxt(request);
		ResponseData responseData = databaseHelpers.addProject(requestBodyTxt);
		if (!responseData.isError()) {
			response.setStatus(201);
		} else {
			response.setStatus(400);
		}
		response.getWriter().write(gson.toJson(responseData));
	}

}
