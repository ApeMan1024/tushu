package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.mapper.Admin;
import com.model.Card;
import com.service.AdministratorsSql;

@WebServlet("/Borrow")
public class Borrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Borrow() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("bo");
		try {
			JSONObject jsonObject = new JSONObject(parameter);
			Admin admin=new AdministratorsSql();
			Card card=new Card();
			card.setName(jsonObject.getString("username_str"));
			card.setNumber(jsonObject.getString("isuserid_str"));
			card.setCareer(jsonObject.getString("ismajor_str"));
			card.setDpt(jsonObject.getString("isdepartment_str"));
			card.setPhone(jsonObject.getString("isuserphone_str"));
			card.setAmount(5);
			card.setTotal(10);
			card.setOwe(0);
			card.setGender(jsonObject.getString("isusersex_str"));
			boolean handle = admin.handle(card);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(handle+"");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
