package com.dataentry.api.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CacheUtil {

	public static final String PROJECT_DATA_LIST = "ProjectDataList";
	public static final String EHCACHE_NAME = "apidatacache";

	public static Cache getCache(String config) {
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(config);
		return cache;
	}
}
