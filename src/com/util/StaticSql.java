package com.util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Card;
import com.model.Exceed;
import com.model.Load;
public class StaticSql {
	static Configuration configuration=null;
	static SessionFactory  sessionFactory=null;
	static Session session=null;
	static {
		configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
		session=sessionFactory.openSession();
	}
	public static Session getSession() {
		return session;
	}
	/**
	 * 判断借阅日期与当前日期是否同�?�?
	 * @param s
	 * @param str
	 * @return
	 */
	private static boolean compare (String s,String str) {
		int [] a=new int [3];
		int [] d=new int [3];
		for(int i=0;i<s.split("-").length;i++) {
			a[i]=Integer.parseInt(s.split("-")[i]);
			d[i]=Integer.parseInt(str.split("-")[i]);
		}
		return a[0]==d[0]&&a[1]==d[1]&&a[2]==d[2];
	}
	/**
	 * 计算借阅日期距当前日期天数，s是�?�阅日期，str是当前日�?
	 * @param s
	 * @param str
	 * @return
	 */
	private static int statistics(String s,String str) {
		int p=0;
		int [] a=new int [3];//借阅日期
		int [] d=new int [3];//当前日期
		for(int i=0;i<s.split("-").length;i++) {
			a[i]=Integer.parseInt(s.split("-")[i]);
			d[i]=Integer.parseInt(str.split("-")[i]);
		}
		for(int i=0;i<a.length;i++) {
			switch(i) {
			case 0:
				if(a[0]!=d[0])p+=(d[0]-a[0])*365;break;
			case 1:
				if(a[1]!=d[1])p+=(d[1]-a[1])*30;break;
			case 2:
				if(a[2]!=d[2])p+=(d[2]-a[2]);break;
			}
		}
		return p;
	}
	/**
	 * 程序自动调用，记录超期情�?
	 */
	public static void refresh() {
		String hql="from Load";
		List<Load> list = session.createQuery(hql, Load.class).list();
		List<String> list2=new ArrayList<String>();
		if(list!=null && list.size()>0) {
			Date date =new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(date);
			for(Load load:list) {
				boolean compare = compare(load.getDate(), format);
				//获取超期借书证号
				if (!compare) {
					int statistics = statistics(load.getDate(), format);
					//借阅期限2个月
					if(statistics>60) {
						list2.add(load.getCard());
						Exceed exceed=new Exceed();
						exceed.setBookname(load.getBookname());
						exceed.setBooknumber(load.getBooknumber());
						exceed.setCard(load.getCard());
						exceed.setName(load.getName());
						exceed.setOwe((statistics-60)*2); 
						Transaction transaction=session.beginTransaction();
						try {
							session.saveOrUpdate(exceed);
							transaction.commit();
						} catch (Exception e) {
							// TODO: handle exception
							transaction.rollback();
							e.printStackTrace();
						}
					}
				}
			}
			
			//统计欠分
			String string = "from Exceed where card=?1";
			for(String s:list2) {
				List<Exceed> list3 = session.createQuery(string, Exceed.class).setParameter(1, s).list();
				if(list3!=null && list3.size()>0) {
					int k=0;
					for(Exceed exceed:list3) {
						k+=exceed.getOwe();
					}
					Card card = session.get(Card.class, s);
					card.setOwe(k);
					Transaction transaction=session.beginTransaction();
					try {
						session.update(card);
						transaction.commit();
					} catch (Exception e) {
						// TODO: handle exception
						transaction.rollback();
						e.printStackTrace();
					}
				}
			}
		}
	}
}
