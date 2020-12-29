package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Master servlet for ajax requests from the 
 * website. Doesn't redirect any pages directly
 * and only sends information back to the frontend.
 * @author Justin W
 *
 */
public class AjaxMasterServlet extends HttpServlet{
	

	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in frontcontroller get");
		
		AjaxRequestHelper.process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in frontcontroller post");
		
		AjaxRequestHelper.process(request, response);
	}

}
