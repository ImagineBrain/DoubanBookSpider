package com.xzx.dbs.spider.bean;

/**
 * @author xiezi 书
 */
public class DoubanBook {
	/**
	 * 序号
	 */
	private String seq;
	/**
	 * 书名
	 */
	private String name;
	/**
	 * 评分
	 */
	private Float rating;
	/**
	 * 评价人数
	 */
	private Integer ratingNum;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 出版社
	 */
	private String publisher;
	/**
	 * 出版日期
	 */
	private String publishDate;
	/**
	 * 价格
	 */
	private String price;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getRatingNum() {
		return ratingNum;
	}

	public void setRatingNum(Integer ratingNum) {
		this.ratingNum = ratingNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((ratingNum == null) ? 0 : ratingNum.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoubanBook other = (DoubanBook) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (ratingNum == null) {
			if (other.ratingNum != null)
				return false;
		} else if (!ratingNum.equals(other.ratingNum))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DoubanBook [seq=" + seq + ", name=" + name + ", rating=" + rating + ", ratingNum=" + ratingNum
				+ ", author=" + author + ", publisher=" + publisher + ", publishDate=" + publishDate + ", price="
				+ price + "]";
	}
}
