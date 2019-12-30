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

import com.model.Pipe;
import com.service.AdministratorsSql;




@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("user");
		try {
			JSONObject jsonObject = new JSONObject(parameter);
			Pipe pipe=new Pipe();
			pipe.setName(jsonObject.getString("username"));
			pipe.setPassword(jsonObject.getString("pword"));
			pipe.setPhone(jsonObject.getString("phone"));
			pipe.setSex(jsonObject.getString("sex"));
			pipe.setBool("false");
			pipe.setEmild(jsonObject.getString("emild"));
			AdministratorsSql administratorsSql=new AdministratorsSql();
			boolean register = administratorsSql.register(pipe);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(register+"");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
