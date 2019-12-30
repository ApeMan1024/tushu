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

import com.mapper.Stu;
import com.model.Book;
import com.service.Stu_sql;

@WebServlet("/Reader")
public class Reader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Reader() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("bo");
		if(parameter.equals("book")) {
			Stu stu=new Stu_sql();
			List<Book> boolBooks = stu.boolBooks();
			JSONArray jsonArray = new JSONArray(boolBooks);
			String string = jsonArray.toString();
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(string);
		}
		else if(parameter.equals("num")) {
			String parameter2 = request.getParameter("numbre");
			Stu stu=new Stu_sql();
			boolean select = stu.select(parameter2);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(select+"");
		}
		else if(parameter.equals("re")) {
			String parameter2 = request.getParameter("json2");
			String number = request.getParameter("k");
			try {
				JSONObject jsonObject = new JSONObject(parameter2);
				Book book=new Book();
				book.setAmount(Integer.parseInt(jsonObject.getString("amount")));
				book.setAppear(jsonObject.getString("appear"));
				book.setAuthor(jsonObject.getString("author"));
				book.setBookname(jsonObject.getString("bookname"));
				book.setBooknumber(jsonObject.getString("booknumber"));
				book.setBookshelf(Integer.parseInt(jsonObject.getString("bookshelf")));
				book.setData(jsonObject.getString("data"));
				book.setTotal(Integer.parseInt(jsonObject.getString("total")));
				Stu stu=new Stu_sql();
				boolean load = stu.load(book, number);
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				List<String>aList=new ArrayList<String>();
				aList.add(load+"");
				List<Book> boolBooks = stu.boolBooks();
				JSONArray jsonArray = new JSONArray(boolBooks);
				String string = jsonArray.toString();
				aList.add(string);
				JSONArray jsonArray2 = new JSONArray(aList);
				String string2 = jsonArray2.toString();
				writer.write(string2);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
