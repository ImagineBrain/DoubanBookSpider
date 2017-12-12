package com.xzx.dbs.spider.util;

import java.util.Comparator;

import com.xzx.dbs.spider.bean.DoubanBook;

/**
 * @author xiezi
 * 豆瓣书籍比较器
 */
public class DoubanBookComparator implements Comparator<DoubanBook> {

	@Override
	public int compare(DoubanBook o1, DoubanBook o2) {
		// TODO Auto-generated method stub
		Float rating1 = o1.getRating() == null ? 0 : o1.getRating();
		Float rating2 = o2.getRating() == null ? 0 : o2.getRating();
		return rating1.compareTo(rating2);
	}
}
