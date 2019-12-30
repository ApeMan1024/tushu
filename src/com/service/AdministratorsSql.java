package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mapper.Admin;
import com.model.Book;
import com.model.Card;
import com.model.Exceed;
import com.model.Librarian;
import com.model.Pipe;
import com.util.StaticSql;

import javafx.scene.control.Alert;

public class AdministratorsSql implements Admin {
	static Session session = StaticSql.getSession();

	public String login(String name, String password) {
		// 登录
		String hql = "from Pipe where name=?1 and password=?2";
		List<Pipe> list = session.createQuery(hql, Pipe.class).setParameter(1, name).setParameter(2, password).list();
		if (list != null && list.size() != 0) {
			session.clear();
			return list.get(0).getBool();
		}
		session.clear();
		return "#";
	}
	public boolean reset(Pipe pipe) {
		Pipe pipe2 = session.get(Pipe.class, pipe.getName());
		if(pipe2==null) {
			return false;
		}
		else {
			pipe2.setPassword(pipe.getPassword());
			System.out.println(pipe2);
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(pipe2);
			transaction.commit();
			return true;
		}
	}
	public List<Card> select() {
		String hql = "from Card";
		List<Card> list = session.createQuery(hql, Card.class).list();
		session.clear();
		return list;
	}
	public Pipe select(Pipe pipe) {
		String hql = "from Pipe where name=?1 and password=?2";
		List<Pipe> setParameter = session.createQuery(hql,Pipe.class).setParameter(1, pipe.getName()).setParameter(2, pipe.getPassword()).list();
		session.clear();
		return setParameter.get(0);
	}
	public List<Book> boolBooks() {
		String hql = "from Book";
		List<Book> list = session.createQuery(hql, Book.class).list();
		session.clear();
		return list;
	}

	public Card edit(Card card) {
		// 编辑
		String hql = "from Exceed where card=?1";
		String hql1 = "from Card where number=?1 and name=?2";
		List<Exceed> list = session.createQuery(hql, Exceed.class).setParameter(1, card.getNumber()).list();
		if (list != null && list.size() != 0) {
			session.clear();
			return null;
		} else {
			session.clear();
			List<Card> list2 = session.createQuery(hql1, Card.class).setParameter(1, card.getNumber())
					.setParameter(2, card.getName()).list();
			if (list2 != null && list2.size() > 0) {
				return list2.get(0);
			}
			return new Card();
		}
	}

	public boolean warehousing(Book book) {
		// 图书入库
		String hql = "from Book where booknumber=?1 and bookname=?2";
		Librarian librarian = session.get(Librarian.class, book.getBookshelf());
		librarian.setTotal(librarian.getTotal() + book.getAmount());
		List<Book> list = session.createQuery(hql, Book.class).setParameter(1, book.getBooknumber())
				.setParameter(2, book.getBookname()).list();
		Book book2 = null;
		book.setTotal(book.getAmount());
		boolean bool = false;
		if (list != null && list.size() != 0) {
			book2 = list.get(0);
			book2.setAmount(book.getAmount() + book2.getAmount());
			book2.setTotal(book2.getTotal() + book.getAmount());
		} else {
			book2 = book;
		}
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(book2);
			session.saveOrUpdate(librarian);
			transaction.commit();
			bool = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.clear();
		return bool;
	}

	public boolean out(Book book) {
		// 图书出库
		
		String hql = "from Book where bookname=?1 and booknumber=?2";
		Librarian librarian = session.get(Librarian.class, book.getBookshelf());
		librarian.setTotal(librarian.getTotal() - book.getTotal());
		List<Book> list = session.createQuery(hql, Book.class).setParameter(1, book.getBookname())
				.setParameter(2, book.getBooknumber()).list();
		if (list.get(0).getAmount() != list.get(0).getTotal()) {
			session.clear();
			return false;
		} else {
			Transaction transaction = session.beginTransaction();
			try {
				session.clear();
				session.delete(book);
				session.saveOrUpdate(librarian);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
				e.printStackTrace();
			}
			session.clear();
			return true;
		}
	}

	public boolean out1(Book book) {
		// 图书出库
		session.clear();
		String hql = "from Book where bookname=?1 and booknumber=?2";
		Librarian librarian = session.get(Librarian.class, book.getBookshelf());
		librarian.setTotal(librarian.getTotal() - book.getTotal());
		List<Book> list = session.createQuery(hql, Book.class).setParameter(1, book.getBookname())
				.setParameter(2, book.getBooknumber()).list();
		if(list.get(0).getAmount()>book.getTotal()) {
			
			Transaction transaction = session.beginTransaction();
			Book book1=list.get(0);
			book1.setAmount(book1.getAmount()-book.getAmount());
			book1.setTotal(book1.getTotal()-book.getTotal());
			try {
				session.saveOrUpdate(book1);
				session.saveOrUpdate(librarian);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
				e.printStackTrace();
			}
			session.clear();
			return true;
		}
		else {
			return false;
		}
	}

	public boolean handle(Card card) {
		// 办理借书�?
		String hql = "from Card where number=?1";
		List<Card> list = session.createQuery(hql, Card.class).setParameter(1, card.getNumber()).list();
		if (list != null && list.size() != 0) {
			return false;
		} else {
			Transaction transaction = session.beginTransaction();
			try {
				session.saveOrUpdate(card);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
				e.printStackTrace();
			}
			return true;
		}
	}

	public boolean alter(Card card) {
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(card);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
			e.printStackTrace();
		}
		return true;
	}

	public List<Exceed> exceed() {
		// 查阅超期情况
		String hql = "from Exceed";
		List<Exceed> list2 = session.createQuery(hql, Exceed.class).list();
		if (list2 != null && list2.size() != 0) {
			session.clear();
			return list2;
		} else {
			session.clear();
			return null;
		}
	}

	public boolean register(Pipe pipe) {
		// 管理员注?
		String hql = "from Pipe where name=?1";
		List<Pipe> list = session.createQuery(hql, Pipe.class).setParameter(1, pipe.getName()).list();
		if (list != null && list.size() != 0) {
			return false;
		} else {
			Transaction transaction = session.beginTransaction();
			try {
				pipe.setPassword(DigestUtils.md5Hex(pipe.getPassword()));
				session.save(pipe);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
				e.printStackTrace();
			}
			return true;
		}
	}
}
