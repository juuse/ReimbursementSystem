package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import service.ErsUserService;
import service.ErsUsersServiceImpl;

public class UserController {
	
	public static ErsUserService userServ = new ErsUsersServiceImpl();
	
	/**
	 * Sends all of the user's logged information through the response
	 */
	public static void showUserData(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		
		User u = (User)request.getSession().getAttribute("loggeduser");
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(u));
				
	}
	
	/**
	 * Sends the map containing the names and ids of every user
	 */
	public static void getMapForNames(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		response.getWriter().write(new ObjectMapper().writeValueAsString(userServ.getNameIDMap()));
	}
}
