package com.mapper;

import java.util.List;
import com.model.Book;
import com.model.Card;
import com.model.Exceed;
import com.model.Pipe;

public interface Admin {
	public  String login(String name,String password);
	public  Card edit(Card card);
	public  boolean warehousing(Book book);
	public boolean out(Book book);
	public boolean handle(Card card) ;
	public boolean alter(Card card);
	public  List<Exceed> exceed();
	public  boolean register(Pipe pipe);
	public List<Card> select();
	public List<Book> boolBooks();
	public boolean out1(Book book);
	public Pipe select(Pipe pipe);
	public boolean reset(Pipe pipe);
}
