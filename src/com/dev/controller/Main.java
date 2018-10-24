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
import com.dev.model.implemenation.Validation;
import com.dev.model.implemenation.ValidationDao;

@WebServlet("/loginServ")
public class Main extends HttpServlet{
	LoginDao ld=new Implimentation(); 
	Credential cre=new Credential();
	ValidationDao valid=new Validation();
	int count=cre.getCount();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		String email=req.getParameter("uname");
		String password=req.getParameter("pass");
		boolean validname=valid.isStringOnlyAlphabet(email);
		boolean validpass=valid.passvalid(password);
		
		if(count<3 && validname && validpass) 
		{
			boolean result=ld.login(email,password);
			if(result) 
				{
						HttpSession session=req.getSession();
						RequestDispatcher dispatcher=req.getRequestDispatcher("");// change as per ur required forward resource
						dispatcher.forward(req,resp);
		    	}
		    else {
						++count;
						cre.setCount(count);
						cre.setStart(Instant.now());
						//System.out.println("failed for "+count);
		     	 }
		}
		else 
		{
						Instant start=cre.getStart();
						Instant stop=Instant.now();
						long differ=Duration.between(start,stop).toMillis();
						if(!validname ||!validpass) {
							RequestDispatcher dispatcher=req.getRequestDispatcher("");// change as per ur required forward resource
							dispatcher.forward(req,resp);
						}
						
						else if(differ<10000)
							{
							//System.out.println("locked");
						RequestDispatcher dispatcher=req.getRequestDispatcher("");// change as per ur required forward resource
						dispatcher.forward(req,resp);
							}
						else {
							RequestDispatcher dispatcher=req.getRequestDispatcher("");// change as per ur required forward resource
							dispatcher.forward(req,resp);
							//System.out.println("you can login  now");
							}
		}
	}

}
