package com.xzx.dbs.spider.start;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.xzx.dbs.spider.bean.DoubanBook;
import com.xzx.dbs.spider.container.AllBookVector;
import com.xzx.dbs.spider.container.BookVector;
import com.xzx.dbs.spider.container.UrlQueue;
import com.xzx.dbs.spider.container.UrlTable;
import com.xzx.dbs.spider.excel.ExcelUtil;
import com.xzx.dbs.spider.thread.SpiderWorker;
import com.xzx.dbs.spider.util.UrlUtil;

public class StartSpider {

	public static void main(String[] args) {
		String type = "编程";
		// 初始化爬取队列
		initializeQueue(type);

		// 创建worker线程并启动
		ExecutorService exe = Executors.newFixedThreadPool(50);
		for (int i = 1; i <= 5; i++) {
			exe.execute(new SpiderWorker(i));
			if (i == 1) {
				try {
					TimeUnit.SECONDS.sleep(4);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		exe.shutdown();
		while (true) {
			if (exe.isTerminated()) {
				// 所有线程执行完毕
				System.out.println("所有线程爬取结束！");
				break;
			}
		}
		System.out.println("所有书籍");
		for (DoubanBook b : AllBookVector.getBooks()) {
			System.out.println(b.toString());
		}
		System.out.println("评价大于1000的前100本书籍");
		for (DoubanBook b : BookVector.getBooks()) {
			System.out.println(b.toString());
		}
		System.out.println("已经爬取的URL");
		Set<String> set = UrlTable.getUrlTable().keySet();
		for (String key : set) {
			System.out.println(UrlTable.getUrlTable().get(key));
		}
		// 生成excel
		ExcelUtil.generateBookExcel("D:", type + ".xls");
		System.out.println("生成Excel结束!");
	}

	/**
	 * 准备初始的爬取链接
	 */
	private static void initializeQueue(String type) {
		UrlQueue.addElement(UrlUtil.BASE_URL + "/tag/" + type + "?start=0&type=S");
	}
}
