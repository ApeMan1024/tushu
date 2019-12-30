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

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.model.Pipe;
import com.service.AdministratorsSql;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("username");
		String parameter2 = request.getParameter("pword");
		parameter2=DigestUtils.md5Hex(parameter2);
		AdministratorsSql administratorsSql=new AdministratorsSql();
		String login = administratorsSql.login(parameter, parameter2);
		if(login.equals("true")) {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			Pipe pipe = new com.model.Pipe();
			pipe.setName(parameter);
			pipe.setPassword(parameter2);
			Pipe select = administratorsSql.select(pipe);
			JSONObject jsonObject = new JSONObject(select);
			String string = jsonObject.toString();
			List<String>aList=new ArrayList<String>();
			aList.add("true");
			aList.add(string);
			JSONArray jsonArray = new JSONArray(aList);
			String string2 = jsonArray.toString();
			writer.write(string2);
		}
		else if(login.equals("false")) {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			Pipe pipe = new com.model.Pipe();
			pipe.setName(parameter);
			pipe.setPassword(parameter2);
			Pipe select = administratorsSql.select(pipe);
			JSONObject jsonObject = new JSONObject(select);
			String string = jsonObject.toString();
			List<String>aList=new ArrayList<String>();
			aList.add("false");
			aList.add(string);
			JSONArray jsonArray = new JSONArray(aList);
			String string2 = jsonArray.toString();
			writer.write(string2);
		}
		else {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write("#");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
