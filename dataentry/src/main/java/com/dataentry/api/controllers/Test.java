package com.dataentry.api.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.helpers.GetTest;
import com.dataentry.api.helpers.TestData;
import com.google.gson.Gson;

public class Test extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		GetTest test = new GetTest();
		ArrayList<TestData> list = test.getLists();
		String txt = gson.toJson(list);
		response.getWriter().write(txt);
	}

}
