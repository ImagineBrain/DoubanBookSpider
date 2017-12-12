package com.xzx.dbs.spider.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.xzx.dbs.spider.bean.FetchedPage;
import com.xzx.dbs.spider.start.StartSpider;

/**
 * @author xiezi
 * 爬取网页工具
 */
public class PageFetcher {
	/**
	 * log
	 */
	private static final Logger log = Logger.getLogger(StartSpider.class.getName());
	/**
	 * http客户端
	 */
	private DefaultHttpClient client;

	/**
	 * 创建HttpClient实例，并初始化连接参数
	 */
	public PageFetcher() {
		// 设置超时时间
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
		HttpConnectionParams.setSoTimeout(params, 10 * 1000);
		client = new DefaultHttpClient(params);
	}

	/**
	 * 主动关闭HttpClient连接
	 */
	public void close() {
		client.getConnectionManager().shutdown();
	}

	/**
	 * 根据url爬取网页内容
	 * 
	 * @param url
	 * @return
	 */
	public FetchedPage getContentFromUrl(String url) {
		String content = null;
		int statusCode = 500;

		// 创建Get请求，并设置Header
		HttpGet getHttp = new HttpGet(url);
		getHttp.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
		HttpResponse response;

		try {
			// 获得信息载体
			response = client.execute(getHttp);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// 转化为文本信息, 设置爬取网页的字符集，防止乱码
				content = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("爬取信息失败", e);
		}

		return new FetchedPage(url, content, statusCode);
	}
}
