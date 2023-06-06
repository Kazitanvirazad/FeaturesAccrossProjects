package com.dataentry.api.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.helpers.DatabaseHelpers;
import com.dataentry.api.response.ResponseData;
import com.google.gson.Gson;

public class DropdownList extends HttpServlet {

	private static final long serialVersionUID = -4420373466561165300L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		DatabaseHelpers databaseHelpers = new DatabaseHelpers();
		ResponseData responseData = databaseHelpers.getDropdownData();
		if (responseData.isError()) {
			response.setStatus(404);
		}

		response.getWriter().write(gson.toJson(responseData));
	}

}
