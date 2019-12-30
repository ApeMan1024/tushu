package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mapper.Admin;
import com.model.Book;
import com.model.Card;
import com.service.AdministratorsSql;

@WebServlet("/Pipe")
public class Pipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Pipe() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parameter = request.getParameter("bo");
		if(parameter.equals("card")) {
			Admin administratorsSql=new AdministratorsSql();
			List<Card> select = administratorsSql.select();
			JSONArray jsonArray = new JSONArray(select);
			String string = jsonArray.toString();
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(string);
		}
		else if(parameter.equals("book")) {
			Admin administratorsSql=new AdministratorsSql();
			List<Book> boolBooks = administratorsSql.boolBooks();
			JSONArray jsonArray = new JSONArray(boolBooks);
			String string = jsonArray.toString();
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(string);
		}
		else if(parameter.equals("book1")) {
			String parameter2 = request.getParameter("json");
			try {
				JSONObject jsonObject = new JSONObject(parameter2);
				Book book=new Book();
				book.setBooknumber(jsonObject.getString("booknumber"));
				book.setBookname(jsonObject.getString("bookname"));
				book.setAmount(Integer.parseInt(jsonObject.getString("liang")));
				book.setAppear(jsonObject.getString("ban"));
				book.setAuthor(jsonObject.getString("zuo"));
				book.setBookshelf(Integer.parseInt(jsonObject.getString("hao")));
				book.setData(jsonObject.getString("chu"));
				Admin admin=new AdministratorsSql();
				boolean warehousing = admin.warehousing(book);
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				if(warehousing) {
					List<Book> boolBooks = admin.boolBooks();
					JSONArray jsonArray = new JSONArray(boolBooks);
					String string = jsonArray.toString();
					writer.write(string);
				}
				else {
					
					writer.write("false");
				}
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		else if(parameter.equals("chu")) {
			Admin admin=new AdministratorsSql();
			Book book=new Book();
			String parameter2 = request.getParameter("json");
			try {
				JSONObject jsonObject = new JSONObject(parameter2);
				book.setBooknumber(jsonObject.getString("booknumber1"));
				book.setBookname(jsonObject.getString("bookname1"));
				book.setAmount(Integer.parseInt(jsonObject.getString("shuliang")));
				book.setTotal(Integer.parseInt(jsonObject.getString("shuliang")));
				book.setBookshelf(Integer.parseInt(jsonObject.getString("shujia")));
				boolean out1 = admin.out1(book);
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				if(out1) {
					List<Book> boolBooks = admin.boolBooks();
					JSONArray jsonArray = new JSONArray(boolBooks);
					String string = jsonArray.toString();
					writer.write(string);
				}
				else {
					writer.write(out1+"");
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			admin.out1(book);
		}
		else if(parameter.equals("chu1")) {
			Admin admin=new AdministratorsSql();
			Book book=new Book();
			String parameter2 = request.getParameter("json");
			try {
				JSONObject jsonObject = new JSONObject(parameter2);
				book.setAmount(Integer.parseInt(jsonObject.getString("amount")));
				book.setAppear(jsonObject.getString("appear"));
				book.setAuthor(jsonObject.getString("author"));
				book.setBookname(jsonObject.getString("bookname"));
				book.setBooknumber(jsonObject.getString("booknumber"));
				book.setBookshelf(Integer.parseInt(jsonObject.getString("bookshelf")));
				book.setData(jsonObject.getString("data"));
				book.setTotal(Integer.parseInt(jsonObject.getString("total")));
				boolean out = admin.out(book);
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				if(out) {
					List<Book> boolBooks = admin.boolBooks();
					JSONArray jsonArray = new JSONArray(boolBooks);
					String string = jsonArray.toString();
					List<String>aList=new ArrayList<String>();
					aList.add(out+"");
					aList.add(string);
					JSONArray jsonArray2 = new JSONArray(aList);
					String string2 = jsonArray2.toString();
					writer.write(string2);
				}
				else {
					writer.write(out+"");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
