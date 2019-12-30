package com.model;

public class Book {
	private String booknumber;//图书编号
	private String bookname;//图书名称
	private String author;//作者
	private String data;//图书日期
	private String appear;//图书出版社
	private int bookshelf;//书架号
	private int amount;//数量
	private int total;//图书总数量
	public Book() {
		super();
	}
	public Book(String booknumber, String bookname, String author, String data, String appear, int bookshelf,
			int amount, int total) {
		super();
		this.booknumber = booknumber;
		this.bookname = bookname;
		this.author = author;
		this.data = data;
		this.appear = appear;
		this.bookshelf = bookshelf;
		this.amount = amount;
		this.total = total;
	}
	public String getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(String booknumber) {
		this.booknumber = booknumber;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getAppear() {
		return appear;
	}
	public void setAppear(String appear) {
		this.appear = appear;
	}
	public int getBookshelf() {
		return bookshelf;
	}
	public void setBookshelf(int bookshelf) {
		this.bookshelf = bookshelf;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Book [booknumber=" + booknumber + ", bookname=" + bookname + ", author=" + author + ", data=" + data
				+ ", appear=" + appear + ", bookshelf=" + bookshelf + ", amount=" + amount + ", total=" + total + "]";
	}
	
}
