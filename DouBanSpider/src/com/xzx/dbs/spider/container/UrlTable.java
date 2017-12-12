package com.xzx.dbs.spider.container;

import java.util.Hashtable;

/**
 * @author xiezi 已爬取过的urltable
 */
public class UrlTable {

	private static Hashtable<String, String> urlTable = new Hashtable<String, String>();

	public static Hashtable<String, String> getUrlTable() {
		return urlTable;
	}

	public synchronized static void put(String key, String value) {
		urlTable.put(key, value);
	}

	public synchronized static void remove(String key) {
		urlTable.remove(key);
	}

	public static String get(String key) {
		return urlTable.get(key);
	}

	public static boolean contains(String value) {
		return urlTable.contains(value);
	}
}
