package com.dev.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.model.beans.Credential;
import com.dev.model.implemenation.Implimentation;
import com.dev.model.implemenation.LoginDao;

@WebServlet("/loginServ")
public class Main extends HttpServlet{
	LoginDao ld=new Implimentation(); 
	Credential cre=new Credential();
	int count=cre.getCount();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		String email=req.getParameter("email");
		String password=req.getParameter("pass");
		
		if(count<3) 
		{
			boolean result=ld.login(email,password);
			if(result) 
				{
						HttpSession session=req.getSession();
						RequestDispatcher dispatcher=req.getRequestDispatcher("");
						dispatcher.forward(req,resp);
		    	}
			else {
						++count;
						cre.setCount(count);
						cre.setStart(Instant.now());;
		     	 }
		}
		else 
		{
						Instant start=cre.getStart();
						Instant stop=Instant.now();
						long differ=Duration.between(start,stop).toMillis();
						if(differ<10000)
							{
						RequestDispatcher dispatcher=req.getRequestDispatcher("");
						dispatcher.forward(req,resp);
							}
						else {
							RequestDispatcher dispatcher=req.getRequestDispatcher("");
							dispatcher.forward(req,resp);
							}
		}
	}

}
