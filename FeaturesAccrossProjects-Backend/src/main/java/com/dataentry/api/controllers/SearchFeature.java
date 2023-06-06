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
import com.google.gson.Gson;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.dataentry.api.response.ResponseData;

public class SearchFeature extends HttpServlet {

	private static final long serialVersionUID = 8694722508658576730L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Cache cache = CacheUtil.getCache(CacheUtil.EHCACHE_NAME);
		DatabaseHelpers databaseHelpers = new DatabaseHelpers();
		String key = request.getParameter("keyword");
		ResponseData responseData = null;
		boolean isCachedDataPresent = false;
		Element element = cache.get(key);
		if (element != null) {
			List<Object> datalist = (List<Object>) element.getObjectValue();
			if (datalist != null && datalist.size() > 0) {
				isCachedDataPresent = true;
				responseData = new ResponseData(false, datalist);
			}
		}

		if (!isCachedDataPresent) {
			responseData = databaseHelpers.getSearchData(key);
		}

		if (responseData.isError()) {
			response.setStatus(404);
		}

		response.getWriter().write(gson.toJson(responseData));
	}

}
