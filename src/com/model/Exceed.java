package com.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Exceed implements Serializable{
	private String card;//借书证号
	private String name;//姓名
	private String booknumber;//图书编号
	private String bookname;//图书名称
	private int    owe;//欠分
	public Exceed() {
		super();
	}
	public Exceed(String card, String name, String booknumber, String bookname, int owe) {
		super();
		this.card = card;
		this.name = name;
		this.booknumber = booknumber;
		this.bookname = bookname;
		this.owe = owe;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getOwe() {
		return owe;
	}
	public void setOwe(int owe) {
		this.owe = owe;
	}
	@Override
	public String toString() {
		return "Exceed [card=" + card + ", name=" + name + ", booknumber=" + booknumber + ", bookname=" + bookname
				+ ", owe=" + owe + "]";
	}
	
}
