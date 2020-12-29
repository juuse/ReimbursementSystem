package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import controller.ReimbursementController;
import controller.UserController;

/**
 * Processes all the ajax requests from the front end and sends them
 * to the proper controller.
 * @author Justin W
 *
 */
public class AjaxRequestHelper {
	
	/**
	 * Checks the request URI and sends the request and response
	 * to the proper location based on what is contained
	 * within the URI.
	 */
	public static void process(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
			case "/project1/api/ajax/userInfo":
				UserController.showUserData(request, response);
				break;
			case "/project1/api/ajax/userReimbursements":
				ReimbursementController.showAllUserReimbs(request, response);
				break;
			case "/project1/api/ajax/newReimbursement":
				ReimbursementController.generateNewReimb(request, response);
				break;
			case "/project1/api/ajax/mapNamesToID":
				UserController.getMapForNames(request, response);
				break;
			case "/project1/api/ajax/everyReimbursement":
				ReimbursementController.showAllReimbs(request, response);
				break;
			case "/project1/api/ajax/pendingReimbursements":
				ReimbursementController.showPendingReimbs(request, response);
				break;
			case "/project1/api/ajax/disapproveReimbursement":
				ReimbursementController.disapproveReimbursements(request, response);
				break;
			case "/project1/api/ajax/approveReimbursement":
				ReimbursementController.approveReimbursements(request, response);
				break;
//			case "/project1/api/ajax/invalidateSession":
//				ReimbursementController.invalidate(request, response);
//				break;
			default:
				response.getWriter().println("null");
				System.out.println("issue");
		}
	}
}
