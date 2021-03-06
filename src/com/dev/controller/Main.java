package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.model.Dao.Implimentation;
import com.dev.model.Dao.LoginDao;
import com.dev.model.beans.Credential;
import com.dev.model.service.RetryLogin;
import com.dev.model.service.Validation;
import com.dev.model.service.ValidationDao;

@WebServlet("/loginServ")
public class Main extends HttpServlet{
	LoginDao ld=new Implimentation(); 
	Credential cre=new Credential();
	ValidationDao valid=new Validation();

	RetryLogin rl=new RetryLogin();

	int count=cre.getCount();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		String email=req.getParameter("name");
		String password=req.getParameter("password");
		boolean validname=valid.isStringOnlyAlphabet(email);
		boolean validpass=valid.passvalid(password);

		if(count<3 && validname && validpass) 
		{
			boolean result=ld.login(email,password);
			if(result) 
			{
				HttpSession session=req.getSession();
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");// change as per ur required forward resource
				//System.out.println("you are logged in");
				dispatcher.forward(req,resp);
				session.setAttribute("user",email);
			}
			else {
				cre.setCount(++count);
				rl.countntime();
				System.out.println("failed for "+count);
				RequestDispatcher dispatcher=req.getRequestDispatcher("fail.jsp");// change as per ur required forward resource

				dispatcher.forward(req,resp);
			}
		}
		else 
		{

			long differ=rl.timeDifference();
			if(!validname ||!validpass) {
				RequestDispatcher dispatcher=req.getRequestDispatcher("fail.jsp");// change as per ur required forward resource
				dispatcher.forward(req,resp);
			}

			else if(differ<10000)
			{
				System.out.println("locked");
				RequestDispatcher dispatcher=req.getRequestDispatcher("lock.jsp");// change as per ur required forward resource
				dispatcher.forward(req,resp);
			}
			else {
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.html");// change as per ur required forward resource
				dispatcher.forward(req,resp);
				count=0;
				cre.setCount(count);
				System.out.println("you can login  now");

			}
		}
	}

}
