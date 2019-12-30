package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.model.Card;
import com.model.Load;
import com.service.Stu_sql;


@WebServlet("/Reader1")
public class Reader1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Reader1() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("bo");
		if(parameter.equals("jie")) {
			String card = request.getParameter("card");
			String name = request.getParameter("name");
			Card card2=new Card();
			card2.setName(name);
			card2.setNumber(card);
			Stu stu=new Stu_sql();
			boolean select = stu.select(card2);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			if(select) {
				List<Load> query = stu.query(card2);
				if(query==null)writer.write("false");
				else {
					JSONArray jsonArray = new JSONArray(query);
					String string = jsonArray.toString();
					writer.write(string);
				}
			}
			else {
				writer.write(select+"");
			}
		}
		else if(parameter.equals("gui")) {
			String parameter2 = request.getParameter("json1");
			try {
				JSONObject jsonObject = new JSONObject(parameter2);
				Load load=new Load();
				load.setBookname(jsonObject.getString("bookname"));
				load.setBooknumber(jsonObject.getString("booknumber"));
				load.setCard(jsonObject.getString("card"));
				load.setDate(jsonObject.getString("date"));
				load.setName(jsonObject.getString("name"));
				Stu stu=new Stu_sql();
				boolean revert = stu.revert(load);
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				if(revert) {
					Card card=new Card();
					card.setName(load.getName());
					card.setNumber(load.getCard());
					List<Load> query = stu.query(card);
					JSONArray jsonArray = new JSONArray(query);
					String string = jsonArray.toString();
					
					writer.write(string);
				}
				else {
					writer.write("false");
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
