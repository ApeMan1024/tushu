package com.model;

public class Librarian {
	private int tower;//楼层号
	private int bookshelf;//书架号
	private int total;//图书总数量
	public Librarian() {
		super();
	}
	public Librarian(int tower, int bookshelf, int total) {
		super();
		this.tower = tower;
		this.bookshelf = bookshelf;
		this.total = total;
	}
	public int getTower() {
		return tower;
	}
	public void setTower(int tower) {
		this.tower = tower;
	}
	public int getBookshelf() {
		return bookshelf;
	}
	public void setBookshelf(int bookshelf) {
		this.bookshelf = bookshelf;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Librarian [tower=" + tower + ", bookshelf=" + bookshelf + ", total=" + total + "]";
	}
	
}
