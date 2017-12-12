package com.xzx.dbs.spider.container;

import java.util.Vector;

import com.xzx.dbs.spider.bean.DoubanBook;
import com.xzx.dbs.spider.util.DoubanBookComparator;

/**
 * @author xiezi
 * 书籍vector
 */
public class BookVector {

	/**
	 * 书籍list
	 */
	private static Vector<DoubanBook> books = new Vector<DoubanBook>();
	private static DoubanBookComparator comparator = new DoubanBookComparator();

	/**
	 * 添加书籍
	 * @param book
	 */
	public synchronized static void addBook(DoubanBook book) {
		if (books.size() < 100) {
			// 低于100本书直接加入
			books.add(book);
		} else {
			DoubanBook minRatingBook = getFirst(); // 评分最低的书
			if (comparator.compare(book, minRatingBook) > 0) {
				// 此书评分比选中的评分最低的书高
				books.remove(0); // 去掉评分最低的书
				books.add(book);
			}
		}
		// 排序
		sort();
	}

	/**
	 * 移除书籍
	 * @param book
	 */
	public synchronized static void removeBook(DoubanBook book) {
		books.remove(book);
	}

	/**
	 * 移除第一个元素
	 */
	public synchronized static void removeFirst() {
		books.remove(0);
	}

	/**
	 * 获取所有书籍
	 * @return
	 */
	public static Vector<DoubanBook> getBooks() {
		return books;
	}

	/**
	 * 获取第一个元素
	 * @return
	 */
	public static DoubanBook getFirst() {
		return books.get(0);
	}

	/**
	 * 顺序排序
	 */
	public synchronized static void sort() {
		books.sort(comparator);
	}

	/**
	 * 书籍数
	 * @return
	 */
	public static int size() {
		return books.size();
	}

	/**
	 * 是否包含书籍
	 * @param b
	 * @return
	 */
	public static boolean contains(DoubanBook b) {
		return books.contains(b);
	}

	/**
	 * 获取第index的书
	 * @param index
	 * @return
	 */
	public static DoubanBook get(int index) {
		return books.get(index);
	}
}
