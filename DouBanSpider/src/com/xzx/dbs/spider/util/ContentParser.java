package com.xzx.dbs.spider.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xzx.dbs.spider.bean.DoubanBook;
import com.xzx.dbs.spider.bean.FetchedPage;

/**
 * @author xiezi 网页上下文解析
 */
public class ContentParser {
	/**
	 * 转换书籍
	 * 
	 * @param fetchedPage
	 * @return
	 */
	public ArrayList<DoubanBook> parseBook(FetchedPage fetchedPage) {
		ArrayList<DoubanBook> targetObjects = new ArrayList<DoubanBook>();
		Document doc = Jsoup.parse(fetchedPage.getContent());
		// 如果当前页面包含目标数据,解析并获取目标数据
		ArrayList<DoubanBook> tempBooks = docToBooks(doc);
		if (tempBooks != null && !tempBooks.isEmpty()) {
			// 解析并获取目标数据
			// TODO
			targetObjects.addAll(tempBooks);
		}
		return targetObjects;
	}

	/**
	 * Doc转书籍
	 * 
	 * @param contentDoc
	 * @return
	 */
	private ArrayList<DoubanBook> docToBooks(Document contentDoc) {
		// 豆瓣书籍列表
		ArrayList<DoubanBook> books = new ArrayList<DoubanBook>();
		// 通过content判断，比如需要抓取class为subject-list中的内容
		Elements siteElements = contentDoc.getElementsByClass("subject-list");
		if (siteElements != null && !siteElements.isEmpty()) {
			Element e = siteElements.first();
			Elements booksElements = e.getElementsByClass("subject-item");
			if (booksElements != null && !booksElements.isEmpty()) {
				for (Element bookElement : booksElements) {
					// 书籍
					DoubanBook book = new DoubanBook();
					// 解析书籍名
					Elements nameEs = bookElement.select("a[title]");
					if (nameEs != null && !nameEs.isEmpty()) {
						Element nameE = nameEs.first();
						book.setName(nameE.attr("title"));
					}
					// 解析出版信息
					Elements pubs = bookElement.getElementsByClass("pub");
					if (pubs != null && !pubs.isEmpty()) {
						Element pubE = pubs.first();
						String pub = pubE.text() == null ? "" : pubE.text();
						String[] pubMsgArray = pub.split("/");
						int len = pubMsgArray.length;
						if (len >= 4) { // 标准出版信息
							book.setPrice(pubMsgArray[len - 1]);
							book.setPublishDate(pubMsgArray[len - 2]);
							book.setPublisher(pubMsgArray[len - 3]);
							String author = "";
							for (int i = 0; i < (len - 3); i++) {
								author += pubMsgArray[i];
							}
							book.setAuthor(author);
						} else {
							book.setPublisher(pub);
						}
					}
					// 解析评价
					Elements ratingElements = bookElement.getElementsByClass("rating_nums");
					if (ratingElements != null && !ratingElements.isEmpty()) {
						Element ratingE = ratingElements.first();
						String ratingStr = ratingE.text();
						Float rating = Float.valueOf(ratingStr);
						book.setRating(rating);
					}
					// 解析评价数
					Elements ratingNumElements = bookElement.getElementsByClass("pl");
					if (ratingNumElements != null && !ratingNumElements.isEmpty()) {
						Element ratingNumE = ratingNumElements.first();
						String tmpRatingNumStr = ratingNumE.text().replaceAll("小于", "").replaceAll("少于", "");
						Pattern patter = Pattern.compile("\\((.*?)人评价\\)");
						Matcher matcher = patter.matcher(tmpRatingNumStr);
						while (matcher.find()) {
							String ratingNumStr = matcher.group(1);
							Integer ratingNum = Integer.valueOf(ratingNumStr);
							book.setRatingNum(ratingNum);
						}
					}
					books.add(book);
				}
			}
		}
		return books;
	}

	/**
	 * 转换url
	 * 
	 * @param fetchedPage
	 * @return
	 */
	public ArrayList<String> parseContainsUrl(FetchedPage fetchedPage) {
		ArrayList<String> targetObjects = new ArrayList<String>();
		Document doc = Jsoup.parse(fetchedPage.getContent());
		// 如果当前页面包含目标数据,解析并获取目标数据
		ArrayList<String> tempUrl = docToUrl(doc);
		if (tempUrl != null && !tempUrl.isEmpty()) {
			// 解析并获取目标数据
			// TODO
			targetObjects.addAll(tempUrl);
		}
		return targetObjects;
	}

	/**
	 * doc转url
	 * 
	 * @param contentDoc
	 * @return
	 */
	private ArrayList<String> docToUrl(Document contentDoc) {
		// 豆瓣书籍列表
		ArrayList<String> urls = new ArrayList<String>();
		// 通过content判断，比如需要抓取class为paginator中的内容
		Elements pageElements = contentDoc.getElementsByClass("paginator");
		if (pageElements != null && !pageElements.isEmpty()) {
			Element e = pageElements.first();
			Elements urlsElements = e.select("a[href]");
			if (urlsElements != null && !urlsElements.isEmpty()) {
				for (Element urlE : urlsElements) {

					String url = urlE.attr("href");
					if (url != null && !url.isEmpty()) {
						urls.add(UrlUtil.BASE_URL + url);
					}
				}
			}
		}
		return urls;
	}
}