package com.xzx.dbs.spider.util;

/**
 * @author xiezi 快速排序
 */
public class QuickSortUtil {

	public static int getMiddle(String[] list, int low, int high) {
		String tmp = list[low]; // 数组的第一个作为中轴
		while (low < high) {
			while (low < high && list[high].compareTo(tmp) >= 0) {
				high--;
			}
			list[low] = list[high]; // 比中轴小的记录移到低端
			while (low < high && list[low].compareTo(tmp) < 0) {
				low++;
			}
			list[high] = list[low]; // 比中轴大的记录移到高端
		}
		list[low] = tmp; // 中轴记录到尾
		return low; // 返回中轴的位置
	}

	public static void quickSort(String[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high); // 将list数组进行一分为二
			quickSort(list, low, middle - 1); // 对低字表进行递归排序
			quickSort(list, middle + 1, high); // 对高字表进行递归排序
		}
	}

	public static void quickSort(String[] a) {
		// 查看数组是否为空
		if (a.length > 0) {
			quickSort(a, 0, a.length - 1);
		}
	}

	public static void main(String[] args) {
		String a[] = { "4.9", "3.8", "6.5", "9.7", "7.6", "1.3", "2.7", "4.9", "7.8", "3.4", "1.2", "6.4", "5", "4",
				"6.2", "9.9", "9.8", "5.4", "5.6", "1.7", "1.8" };
		quickSort(a);
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}