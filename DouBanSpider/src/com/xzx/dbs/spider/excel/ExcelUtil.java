package com.xzx.dbs.spider.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.xzx.dbs.spider.bean.DoubanBook;
import com.xzx.dbs.spider.container.AllBookVector;
import com.xzx.dbs.spider.container.BookVector;

/**
 * @author xiezi
 * excel工具类
 */
public class ExcelUtil {

	/**
	 * log
	 */
	private static final Logger log = Logger.getLogger(ExcelUtil.class.getName());

	/**
	 * Excel标题
	 */
	private static final String[] TITLES = { "序号", "书名", "评分", "评价人数", "作者", "出版社", "出版日期", "价格" };

	/**
	 * 生成书籍excel
	 * 
	 * @param books
	 * @param fileDir
	 */
	public static void generateBookExcel(String fileDir, String fileName) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 新建工作表
		HSSFSheet sheet1 = workbook.createSheet("超过1000评价的前100本书");
		writeBookSheet(workbook, sheet1, BookVector.getBooks());
		HSSFSheet sheet2 = workbook.createSheet("所有书籍");
		writeBookSheet(workbook, sheet2, AllBookVector.getBooks());
		// 写文件
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(fileDir + "/" + fileName));
			workbook.write(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("生成excel失败", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("生成excel失败", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("生成excel失败", e);
				}
			}
		}
	}

	/**
	 * 书籍信息写入sheet
	 * 
	 * @param workbook
	 * @param sheet
	 * @param books
	 */
	public static void writeBookSheet(HSSFWorkbook workbook, HSSFSheet sheet, Vector<DoubanBook> books) {
		// 设置设置标题
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		// 创建字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setBold(true);
		// 加载字体
		titleStyle.setFont(titleFont);
		for (int i = 0; i < TITLES.length; i++) {
			String title = TITLES[i];
			HSSFCell cell = titleRow.createCell(i);
			cell.setCellStyle(titleStyle);
			cell.setCellValue(title);
		}
		// 写入书籍信息
		for (int i = 1; i <= books.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			DoubanBook book = books.get(books.size() - i);
			for (int j = 0; j < TITLES.length; j++) {
				HSSFCell cell = row.createCell(j);
				switch (j) {
				case 0:
					cell.setCellValue(i);
					break;
				case 1:
					cell.setCellValue(book.getName());
					break;
				case 2:
					Float rating = book.getRating();
					if (rating == null) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(book.getRating());
					}
					break;
				case 3:
					Integer ratingNum = book.getRatingNum();
					if (ratingNum == null) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(ratingNum);
					}
					break;
				case 4:
					cell.setCellValue(book.getAuthor());
					break;
				case 5:
					cell.setCellValue(book.getPublisher());
					break;
				case 6:
					cell.setCellValue(book.getPublishDate());
					break;
				case 7:
					cell.setCellValue(book.getPrice());
					break;
				default:
					cell.setCellValue("");
				}
			}
		}
	}
}
