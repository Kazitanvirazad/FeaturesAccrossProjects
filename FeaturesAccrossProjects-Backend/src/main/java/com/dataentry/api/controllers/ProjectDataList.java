package com.dataentry.api.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataentry.api.cache.CacheUtil;
import com.dataentry.api.dao.Project;
import com.dataentry.api.helpers.DatabaseHelpers;
import com.dataentry.api.response.ResponseData;
import com.google.gson.Gson;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class ProjectDataList extends HttpServlet {

	private static final long serialVersionUID = 7997187297618911096L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		DatabaseHelpers databaseHelpers = new DatabaseHelpers();
		boolean isCachedDataPresent = false;

		Cache cache = CacheUtil.getCache(CacheUtil.EHCACHE_NAME);
		ResponseData responseData = null;
		if (cache != null) {
			// Get the element from cache
			Element element = cache.get(CacheUtil.PROJECT_DATA_LIST);
			if (element != null) {
				List<Project> projectList = (List<Project>) element.getObjectValue();
				if (projectList != null && projectList.size() > 0) {
					isCachedDataPresent = true;
					responseData = new ResponseData(false, projectList);
				}
			}
		}

		if (!isCachedDataPresent) {
			responseData = databaseHelpers.getProjectsData();
		}

		if (responseData.isError()) {
			response.setStatus(404);
		}
		response.getWriter().write(gson.toJson(responseData));
	}

}
