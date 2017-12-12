package com.xzx.dbs.spider.bean;

/**
 * @author xiezi
 * 抓取的页面
 */
public class FetchedPage {
	/**
	 * url
	 */
	private String url;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 状态码
	 */
	private int statusCode;

	public FetchedPage() {

	}

	public FetchedPage(String url, String content, int statusCode) {
		this.url = url;
		this.content = content;
		this.statusCode = statusCode;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
