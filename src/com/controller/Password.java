package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.mapper.Admin;
import com.model.Pipe;
import com.service.AdministratorsSql;


@WebServlet("/Password")
public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Password() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String parameter = request.getParameter("json");
			try {
				JSONObject jsonObject = new JSONObject(parameter);
				Pipe pipe = new com.model.Pipe();
				pipe.setName(jsonObject.getString("username"));
				pipe.setPassword(jsonObject.getString("pass"));
				pipe.setPassword(DigestUtils.md5Hex(pipe.getPassword()));
				Admin admin=new AdministratorsSql();
				boolean reset = admin.reset(pipe);
				response.setCharacterEncoding("utf-8");
				PrintWriter writer = response.getWriter();
				if(reset) {
					writer.write(""+1);
				}
				else {
					writer.write(""+0);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
