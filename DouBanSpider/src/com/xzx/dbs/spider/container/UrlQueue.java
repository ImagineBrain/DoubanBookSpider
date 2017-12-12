package com.xzx.dbs.spider.container;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author xiezi 需要爬取的url队列
 */
public class UrlQueue {

	private static LinkedList<String> urlQueue = new LinkedList<String>();

	public synchronized static void addElement(String url) {
		urlQueue.add(url);
	}

	public synchronized static void addFirstElement(String url) {
		urlQueue.addFirst(url);
	}

	public synchronized static String outElement() {
		return urlQueue.removeFirst();
	}

	public synchronized static boolean isEmpty() {
		return urlQueue.isEmpty();
	}

	public static int size() {
		return urlQueue.size();
	}

	public static boolean isContains(String url) {
		return urlQueue.contains(url);
	}

	public synchronized static void addAll(ArrayList<String> urls) {
		urlQueue.addAll(urls);
	}
}
