package com.service;

import java.text.SimpleDateFormat;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mapper.Stu;
import com.model.Book;
import com.model.Card;
import com.model.Exceed;
import com.model.Load;
import com.util.StaticSql;

public class Stu_sql implements Stu {
	static Session se = StaticSql.getSession();

	public ArrayList<Book> query(String number) {
		// 按编号查询
		ArrayList<Book> arrayList = null;
		Book book = se.get(Book.class, number);
		if (book != null) {
			arrayList = new ArrayList<Book>();
			arrayList.add(book);
		}
		se.clear();
		return arrayList;
	}

	public boolean revert(Load load) {
		String hql1 = "from Load where card=?1 and booknumber=?2";
		String hql2 = "from Book where booknumber=?1";
		String hql3 = "from Exceed where card=?1 and booknumber=?2";
		List<Load> list = se.createQuery(hql1, Load.class).setParameter(1, load.getCard())
				.setParameter(2, load.getBooknumber()).list();
		List<Book> list2 = se.createQuery(hql2, Book.class).setParameter(1, load.getBooknumber()).list();
		List<Exceed> list3 = se.createQuery(hql3, Exceed.class).setParameter(1, load.getCard())
				.setParameter(2, load.getBooknumber()).list();
		Transaction beginTransaction = se.beginTransaction();
		try {
			se.clear();
			se.delete(list.get(0));
			Book book = list2.get(0);
			book.setAmount(book.getAmount() + 1);
			se.saveOrUpdate(book);
			if (list3.size() != 0) {
				se.delete(list3.get(0));
			}
			beginTransaction.commit();
		} catch (Exception e) {
			beginTransaction.rollback();
			e.printStackTrace();
		}
		return true;
	}

	public List<Book> boolBooks() {
		String hql = "from Book";
		List<Book> list = se.createQuery(hql, Book.class).list();
		se.clear();
		return list;
	}

	public boolean select(String number) {
		String hql = "from Card where number=?1";
		List<Card> list = se.createQuery(hql, Card.class).setParameter(1, number).list();
		if (list.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean select(Card card) {
		String hql = "from Card where number=?1 and name=?2";

		List<Card> list = se.createQuery(hql, Card.class).setParameter(1, card.getNumber())
				.setParameter(2, card.getName()).list();
		if (list.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Load> query(Card card) {
		String hql = "from Load where card=?1 and name=?2";
		List<Load> list = se.createQuery(hql, Load.class).setParameter(1, card.getNumber())
				.setParameter(2, card.getName()).list();
		
		if (list.size() == 0)
			return null;
		else
			return list;
	}

	public ArrayList<Book> query1(String name) {
		// 按名称查询
		ArrayList<Book> arrayList = null;
		String hql = "from Book where bookname=?1";
		Query<Book> createQuery = se.createQuery(hql, Book.class).setParameter(1, name);
		List<Book> list = createQuery.list();
		if (list.size() != 0) {
			arrayList = new ArrayList<Book>();
			for (Book book : list) {
				arrayList.add(book);
			}
		}
		se.clear();
		return arrayList;
	}

	public boolean load(Book book, String number) {
		Card card2 = se.get(Card.class, number);
		String hql1 = "from Load where card=?1";
		String hql2 = "from Book where booknumber=?1 and bookname=?2";
		List<Book> list2 = se.createQuery(hql2, Book.class).setParameter(1, book.getBooknumber())
				.setParameter(2, book.getBookname()).list();
		List<Load> list = se.createQuery(hql1, Load.class).setParameter(1, number).list();

		if (card2.getOwe() != 0 || list.size() == card2.getAmount()) {
			System.out.println(card2.getAmount());
			se.clear();
			return false;
		} else {
			list2.get(0).setAmount(list2.get(0).getAmount() - 1);
			Load load1 = new Load();
			load1.setBookname(book.getBookname());
			load1.setBooknumber(book.getBooknumber());
			load1.setCard(number);
			load1.setName(card2.getName());
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(date);
			load1.setDate(time);
			Transaction beginTransaction = se.beginTransaction();
			try {
				se.clear();
				se.saveOrUpdate(list2.get(0));
				se.saveOrUpdate(load1);
				beginTransaction.commit();
			} catch (Exception e) {
				beginTransaction.rollback();
				e.printStackTrace();
			}
			se.clear();
			return true;
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public int load(String card, String booknumber, String bookname) {
		// 借阅
		// -1:没有申请借书证 0:欠分或有超期图书未还，不允许借阅 1:借阅图书数量上限 2:图书不存在 3:不允许重复借阅图书 4:借阅成功
		Card card2 = se.get(Card.class, card);
		String hql1 = "from Load where card=?1";
		List<Load> list = se.createQuery(hql1, Load.class).setParameter(1, card).list();
		String hql2 = "from Book where booknumber=?1 and bookname=?2";
		List<Book> list2 = se.createQuery(hql2, Book.class).setParameter(1, booknumber).setParameter(2, bookname)
				.list();
		if (card2 == null) {
			se.clear();
			return -1;
		} else if (card2.getOwe() != 0) {
			se.clear();
			return 0;
		} else if (list.size() >= card2.getAmount()) {
			se.clear();
			return 1;
		} else if (list2.size() == 0) {
			se.clear();
			return 2;
		} else if (list.contains(list2.get(0))) {
			se.clear();
			return 3;
		} else {
			list2.get(0).setAmount(list2.get(0).getAmount() - 1);
			Load load1 = new Load();
			load1.setBookname(bookname);
			load1.setBooknumber(booknumber);
			load1.setCard(card);
			load1.setName(card2.getName());
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(date);
			load1.setDate(time);
			Transaction beginTransaction = se.beginTransaction();
			try {
				se.update(list2.get(0));
				se.update(load1);
				beginTransaction.commit();
			} catch (Exception e) {
				beginTransaction.rollback();
				e.printStackTrace();
			}
			se.clear();
			return 4;
		}
	}

	public int repay(String card, String booknumber, String bookname) {
		// 归还
		// -1:借书证号不存在 0:信息输入错误 1:归还成功，有超期书未还 2:归还成功
		Card card2 = se.get(Card.class, card);
		String hql1 = "from Load where card=?1 and booknumber=?2 and bookname=?3";
		List<Load> list = se.createQuery(hql1, Load.class).setParameter(1, card).setParameter(2, booknumber)
				.setParameter(3, bookname).list();
		if (card2 == null) {
			se.clear();
			return -1;
		} else if (list.size() == 0) {
			se.clear();
			return 0;
		} else {
			String hql2 = "from Exceed where card=?1 and booknumber=?2 and bookname=?3";
			String hql4 = "from Book where bookname=?1 and booknumber=?2";
			List<Exceed> list2 = se.createQuery(hql2, Exceed.class).setParameter(1, card).setParameter(2, booknumber)
					.setParameter(3, bookname).list();
			Book book = se.createQuery(hql4, Book.class).setParameter(1, bookname).setParameter(2, booknumber).list()
					.get(0);
			Transaction transaction = se.beginTransaction();
			try {
				book.setAmount(book.getAmount() + 1);
				se.update(book);
				if (list2.size() != 0) {
					se.delete(list.get(0));
					se.delete(list2.get(0));
				} else {
					se.delete(list.get(0));
				}
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
			se.clear();
			String hql3 = "from Exceed where card=?1";
			List<Exceed> list3 = se.createQuery(hql3, Exceed.class).setParameter(1, card).list();
			if (list3.size() == 0) {
				se.clear();
				return 2;
			} else {
				se.clear();
				return 1;
			}
		}
	}

	public int add(String card, String booknumber, String bookname) {
		// 续借
		// -1:信息输入有错误，重新输入 0:欠分或有超期书未还，不允许续借 1:续借成功
		String hql1 = "from Load where card=?1 and booknumber=?2 and bookname=?3";
		String hql2 = "from Card where number=?1";
		List<Load> list = se.createQuery(hql1, Load.class).setParameter(1, card).setParameter(2, booknumber)
				.setParameter(3, bookname).list();
		List<Card> list2 = se.createQuery(hql2, Card.class).setParameter(1, card).list();
		if (list.size() == 0) {
			se.clear();
			return -1;
		} else if (list2.get(0).getOwe() != 0) {
			se.clear();
			return 0;
		} else {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(date);
			Load load = list.get(0);
			load.setDate(time);
			Transaction transaction = se.beginTransaction();
			try {
				se.update(load);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
			return 1;
		}
	}
}
