package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HomeController;
import controller.LoginController;

/**
 * Processes all the forwarding requests from the front end and sends them
 * to the proper controller.
 * @author Justin W
 *
 */
public class RequestHelper {

	/**
	 * Checks the request URI and sends the request and response
	 * to the proper location based on what is contained
	 * within the URI.
	 */
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("\t\tin request helper");
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
			case "/project1/api/forwarding/login":
				System.out.println("case1");
				return LoginController.login(request);
			case "/project1/api/forwarding/home":
				System.out.println("checkpoint 2");
				return HomeController.home(request);
			case "/project1/api/forwarding/invalidateSession":
				return LoginController.logout(request, response);
			default:
				System.out.println("Bad checkpoint");
				return "/resources/html/loginpage.html";
		}
		
	}

}
