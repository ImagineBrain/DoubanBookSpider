package com.xzx.dbs.spider.container;

import java.util.Hashtable;

/**
 * @author xiezi 已爬取过的urltable
 */
public class UrlTable {

	/**
	 * 已经爬取过的URLtable
	 */
	private static Hashtable<String, String> urlTable = new Hashtable<String, String>();

	/**
	 * 获取已经爬取过的urltable
	 * 
	 * @return
	 */
	public static Hashtable<String, String> getUrlTable() {
		return urlTable;
	}

	/**
	 * 将url标记为已爬取
	 * 
	 * @param key
	 * @param value
	 */
	public synchronized static void put(String key, String value) {
		urlTable.put(key, value);
	}

	/**
	 * 移除url
	 * 
	 * @param key
	 */
	public synchronized static void remove(String key) {
		urlTable.remove(key);
	}

	/**
	 * 获取key指向的url
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return urlTable.get(key);
	}

	/**
	 * 是否包含URL
	 * 
	 * @param value
	 * @return
	 */
	public static boolean contains(String value) {
		return urlTable.contains(value);
	}
}
