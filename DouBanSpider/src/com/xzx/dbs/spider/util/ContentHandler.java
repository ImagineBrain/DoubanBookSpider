package com.xzx.dbs.spider.util;

import com.xzx.dbs.spider.bean.FetchedPage;

public class ContentHandler {
	public boolean check(FetchedPage fetchedPage) {
		// 如果抓取的页面不能获取
		if (isAntiScratch(fetchedPage)) {
			return false;
		}
		return true;
	}

	private boolean isStatusValid(int statusCode) {
		if (statusCode >= 200 && statusCode < 400) {
			return true;
		}
		return false;
	}

	private boolean isAntiScratch(FetchedPage fetchedPage) {
		// 403 forbidden
		if ((!isStatusValid(fetchedPage.getStatusCode())) && fetchedPage.getStatusCode() == 403) {
			return true;
		}
		// 页面内容包含的反爬取内容
		if (fetchedPage.getContent().contains("<div>禁止访问</div>")) {
			return true;
		}
		// 爬取结束
		if (fetchedPage.getContent().contains("没有找到符合条件的图书")) {
			return true;
		}
		return false;
	}
}
