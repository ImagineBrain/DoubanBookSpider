package com.xzx.dbs.spider.container;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author xiezi 需要爬取的url队列
 */
public class UrlQueue {

	/**
	 * 待爬取的url队列
	 */
	private static LinkedList<String> urlQueue = new LinkedList<String>();

	/**
	 * 加入url
	 * 
	 * @param url
	 */
	public synchronized static void addElement(String url) {
		urlQueue.add(url);
	}

	/**
	 * 将url加入队列顶
	 * 
	 * @param url
	 */
	public synchronized static void addFirstElement(String url) {
		urlQueue.addFirst(url);
	}

	/**
	 * 从队列中取出第一个url
	 * 
	 * @return
	 */
	public synchronized static String outElement() {
		return urlQueue.removeFirst();
	}

	/**
	 * 队列是否为空
	 * 
	 * @return
	 */
	public synchronized static boolean isEmpty() {
		return urlQueue.isEmpty();
	}

	/**
	 * 队列长度
	 * 
	 * @return
	 */
	public static int size() {
		return urlQueue.size();
	}

	/**
	 * 队列是否包含url
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isContains(String url) {
		return urlQueue.contains(url);
	}

	/**
	 * 将list中全部url加入队列
	 * 
	 * @param urls
	 */
	public synchronized static void addAll(ArrayList<String> urls) {
		urlQueue.addAll(urls);
	}
}
