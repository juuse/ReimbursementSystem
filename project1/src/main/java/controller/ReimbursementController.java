package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Reimbursement;
import model.User;
import model.Reimbursement.ReimbType;
import service.ErsReimbursementService;
import service.ErsReimbursementServiceImpl;

public class ReimbursementController {
	
	
	final static Logger loggy = Logger.getLogger(ReimbursementController.class);
	public static ErsReimbursementService reimbServ = new ErsReimbursementServiceImpl();
	
	static {
		loggy.setLevel(Level.ALL);
		//loggy.info("This is info: this is the logger my guy");
		//loggy.warn("This is a warning: please stop that");
	}
	
	/**
	 * Shows all of the reimbursements for the user 
	 * currently logged in
	 */
	public static void showAllUserReimbs(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		/*
		 * go to databases at this spot
		 * call service layers
		 */
		User u = (User)request.getSession().getAttribute("loggeduser");	
		List<Reimbursement> reimbs = reimbServ.getAllUserReimbursements(u.getUserId());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));	
	}
	
	/**
	 * Uses the service layer to request to generate a new reimbursement
	 * given the reimbursement amount, description, and type from the request
	 */
	public static void generateNewReimb(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException{
		
		response.setContentType("application/json");
		
		//extract form data
		String amount = request.getParameter("amount");
		String descr = request.getParameter("description");
		String reType = request.getParameter("typing");
		
		System.out.println(amount);
		System.out.println(descr);
		System.out.println(reType);
		ReimbType type = ReimbType.valueOf(reType);
		
		double a = Double.parseDouble(amount);
		
		User u = (User)request.getSession().getAttribute("loggeduser");	
		
		boolean created = reimbServ.submitReimbursement(a, descr, u.getUserId(), type);
		
		if(created) {
			loggy.info("New reimbursement created by user id " + u.getUserId());
			response.getWriter().write(new ObjectMapper().writeValueAsString("Your ticket has been successfully created."));
		}else {
			loggy.warn("User id " + u.getUserId() + " tried to create an invalid reimbursement");
			response.getWriter().write(new ObjectMapper().writeValueAsString("Your ticket failed to be generated."));
		}
	}
	
	/**
	 * Gets every single reimbursement in the database currently
	 */
	public static void showAllReimbs(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		
		List<Reimbursement> reimbs = reimbServ.getAllReimbursementHistory();
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));	
	}
	
	/**
	 * Gets every reimbursement with the status of pending in the database
	 */
	public static void showPendingReimbs(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		
		List<Reimbursement> reimbs = reimbServ.getAllPendingReimbursements();
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));	
	}
	
	/**
	 * Attempts to deny the reimbursement with the ID
	 * given in the request
	 */
	public static void disapproveReimbursements(HttpServletRequest request, HttpServletResponse response) {
		String reimbID = request.getParameter("reimbID");
		User u = (User)request.getSession().getAttribute("loggeduser");	
		
		loggy.info("Reimbursement number " + reimbID + " denied");
		reimbServ.disapproveReimbursement(Integer.parseInt(reimbID), u.getUserId());
	}
	
	/**
	 * Attempts to approve the reimbursement with the ID
	 * given in the request
	 */
	public static void approveReimbursements(HttpServletRequest request, HttpServletResponse response) {
		String reimbID = request.getParameter("reimbID");
		User u = (User)request.getSession().getAttribute("loggeduser");	
		
		loggy.info("Reimbursement number " + reimbID + " approved");
		reimbServ.approveReimbursement(Integer.parseInt(reimbID), u.getUserId());
	}

}
