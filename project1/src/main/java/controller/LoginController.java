package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.ErsUserService;
import service.ErsUsersServiceImpl;

public class LoginController {
	
	private static ErsUserService userServ = new ErsUsersServiceImpl();
	
	public static String login(HttpServletRequest request) {
		
		/*
		 * route guarding:
		 * check if admin token or if correct http method etc
		 */
		if(!request.getMethod().contentEquals("POST")) {
			return "/resources/html/loginpage.html";
		}
		
		//extract form data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//check to see if correct username and password
		if(userServ.verifyLoginCredentials(username, password)) {
			ReimbursementController.loggy.info("User with given credentials logged in " + username + " " + password);
			//make an object for user
			User u = userServ.getUserByCredentials(username, password);
			request.getSession().setAttribute("loggeduser", u);
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", password);
			return "/api/forwarding/home";
		}else {
			return "/api/forwarding/incorrectcredentials";
		}
	}
	
	//Attempted servlet logout
	public static String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession(false).invalidate();
		return "/resources/html/loginpage.html";
	}
}
