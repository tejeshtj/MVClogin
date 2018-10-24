package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.model.beans.*;

@WebServlet("/serv")
public class Serv extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Person p=new Person();
	p.setAddress("ongole");
	p.setEmail("mail1");
	p.setId(123);
	p.setName("tejesh");
	
	RequestDispatcher dis=req.getRequestDispatcher("Home.jsp");
	req.setAttribute("person", p);
	dis.forward(req,resp);
	



}
	
	

}
