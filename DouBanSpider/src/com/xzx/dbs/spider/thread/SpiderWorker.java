package com.xzx.dbs.spider.thread;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.xzx.dbs.spider.bean.DoubanBook;
import com.xzx.dbs.spider.bean.FetchedPage;
import com.xzx.dbs.spider.container.AllBookVector;
import com.xzx.dbs.spider.container.BookVector;
import com.xzx.dbs.spider.container.UrlQueue;
import com.xzx.dbs.spider.container.UrlTable;
import com.xzx.dbs.spider.util.ContentHandler;
import com.xzx.dbs.spider.util.ContentParser;
import com.xzx.dbs.spider.util.PageFetcher;

/**
 * @author xiezi
 * 爬虫线程
 */
public class SpiderWorker implements Runnable {
	/**
	 * log
	 */
	private static final Logger log = Logger.getLogger(SpiderWorker.class.getName());
	private PageFetcher fetcher;
	private ContentHandler handler;
	private ContentParser parser;
	// private DataStorage store;
	private int threadIndex;

	public SpiderWorker(int threadIndex) {
		this.threadIndex = threadIndex;
		this.fetcher = new PageFetcher();
		this.handler = new ContentHandler();
		this.parser = new ContentParser();
		// this.store = new DataStorage();
	}

	@Override
	public void run() {
		// 当待抓取URL队列不为空时，执行爬取任务
		while (!UrlQueue.isEmpty()) {
			// 从待抓取队列中拿URL
			String url = UrlQueue.outElement();
			if (!UrlTable.contains(url)) { // 此页面未爬取过
				// 将此页面标记为已爬取
				UrlTable.put(url, url);
				// 抓取URL指定的页面，并返回状态码和页面内容构成的FetchedPage对象
				FetchedPage fetchedPage = fetcher.getContentFromUrl(url);
				// log.info(fetchedPage.getContent());
				// 检查爬取页面的合法性，爬虫是否被禁止
				if (!handler.check(fetchedPage)) {
					continue;
				}
				// 解析页面，获取目标数据
				ArrayList<DoubanBook> books = parser.parseBook(fetchedPage);
				if (books != null && !books.isEmpty()) {
					for (DoubanBook book : books) {
						AllBookVector.addBook(book); // 记录此书
						Integer ratingNum = book.getRatingNum();
						if (ratingNum >= 1000) {
							// 评价超过1000才有效
							BookVector.addBook(book); // 记录满足要求的书
						}
					}
				}
				// 获取其他URL
				ArrayList<String> containsUrl = parser.parseContainsUrl(fetchedPage);
				UrlQueue.addAll(containsUrl);
			}
		}
		fetcher.close();
		log.info("Spider-" + threadIndex + ": 运行结束");
	}
}
