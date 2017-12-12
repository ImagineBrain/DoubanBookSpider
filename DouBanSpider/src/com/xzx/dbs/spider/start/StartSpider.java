package com.xzx.dbs.spider.start;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.xzx.dbs.spider.bean.DoubanBook;
import com.xzx.dbs.spider.container.AllBookVector;
import com.xzx.dbs.spider.container.BookVector;
import com.xzx.dbs.spider.container.UrlQueue;
import com.xzx.dbs.spider.container.UrlTable;
import com.xzx.dbs.spider.excel.ExcelUtil;
import com.xzx.dbs.spider.thread.SpiderWorker;
import com.xzx.dbs.spider.util.UrlUtil;

/**
 * @author xiezi
 * 启动爬虫
 */
public class StartSpider {
	/**
	 * log
	 */
	private static final Logger log = Logger.getLogger(StartSpider.class.getName());
	/**
	 * 书籍类型
	 */
	private static String type;
	/**
	 * 文件存放路径
	 */
	private static String dirPath;
	/**
	 * 线程数
	 */
	private static int threadNum = 1;
	/**
	 * 运行main
	 * @param args type threadNum dirPath
	 */
	public static void main(String[] args) {
		if (args.length >=3) {
			type = args[0];
			threadNum = Integer.parseInt(args[1]);
			dirPath = args[2];
		}
		// 初始化爬取队列
		initializeQueue(type);
		// 创建worker线程并启动
		ExecutorService exe = Executors.newFixedThreadPool(50);
		for (int i = 1; i <= threadNum; i++) {
			exe.execute(new SpiderWorker(i));
			if (i == 1) {
				try {
					TimeUnit.SECONDS.sleep(4); //等待4s，方便第一个线程从首页获取到了其他页面链接
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("爬虫线程启动失败", e);
				}
			}
		}
		exe.shutdown();
		while (true) {
			if (exe.isTerminated()) {
				// 所有线程执行完毕
				log.info("所有线程爬取结束！");
				break;
			}
		}
		log.info("所有书籍");
		for (DoubanBook b : AllBookVector.getBooks()) {
			log.info(b.toString());
		}
		log.info("评价大于1000的前100本书籍");
		for (DoubanBook b : BookVector.getBooks()) {
			log.info(b.toString());
		}
		log.info("已经爬取的URL");
		Set<String> set = UrlTable.getUrlTable().keySet();
		for (String key : set) {
			log.info(UrlTable.getUrlTable().get(key));
		}
		// 生成excel
		ExcelUtil.generateBookExcel(dirPath, type + ".xls");
		log.info("生成Excel结束!");
	}

	/**
	 * 准备初始的爬取链接
	 */
	private static void initializeQueue(String type) {
		UrlQueue.addElement(UrlUtil.BASE_URL + "/tag/" + type + "?start=0&type=S");
	}
}
