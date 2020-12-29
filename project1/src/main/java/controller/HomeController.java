package controller;

import javax.servlet.http.HttpServletRequest;

import model.User;
import model.User.Role;


//this is the home page after login
public class HomeController {
	
	//Directs the user to the right home page
	//based on whether they are an employee or a finance manager
	public static String home(HttpServletRequest request) {
		User u = (User)request.getSession().getAttribute("loggeduser");
		if(u.getRole() == Role.FINANCE_M)
			return "/resources/html/financemanhome.html";
		else
			return "/resources/html/employeehome.html";
	}
}
