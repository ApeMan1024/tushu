package com.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Load implements Serializable{
	private String card;//借书证号
	private String booknumber;//图书编号
	private String bookname;//图书名称
	private String name;//姓名
	private String date;//时间
	public Load() {
		super();
	}
	public Load(String card, String booknumber, String bookname, String name, String date) {
		super();
		this.card = card;
		this.booknumber = booknumber;
		this.bookname = bookname;
		this.name = name;
		this.date = date;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Load [card=" + card + ", booknumber=" + booknumber + ", bookname=" + bookname + ", name=" + name
				+ ", date=" + date + "]";
	}
	
}
