package com.mapper;

import java.util.ArrayList;
import java.util.List;

import com.model.Book;
import com.model.Card;
import com.model.Load;

public interface Stu {
	public  ArrayList<Book> query(String number);
	public  ArrayList<Book> query1(String name);
	public  int load(String card,String booknumber,String bookname);
	public  int repay(String card,String booknumber,String bookname);
	public  int  add(String card,String booknumber,String bookname) ;
	public List<Book> boolBooks();
	public boolean select(String number) ;
	public boolean load(Book book,String number);
	public boolean select(Card card);
	public List<Load> query(Card card);
	public boolean  revert(Load load);
}
