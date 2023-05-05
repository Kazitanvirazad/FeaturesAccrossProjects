package com.dataentry.api.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.helpers.DatabaseHelpers;
import com.google.gson.Gson;
import com.dataentry.api.response.ResponseData;

public class SearchFeature extends HttpServlet {

	private static final long serialVersionUID = 8694722508658576730L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		DatabaseHelpers databaseHelpers = new DatabaseHelpers();
		String key = request.getParameter("keyword");
		ResponseData responseData = databaseHelpers.getSearchData(key);
		if (responseData.isError()) {
			response.setStatus(404);
		}

		response.getWriter().write(gson.toJson(responseData));
	}

}
