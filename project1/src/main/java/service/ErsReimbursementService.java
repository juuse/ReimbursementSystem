package service;

import java.util.List;

import model.Reimbursement;
import model.Reimbursement.ReimbType;

/**
 * The service layer in charge of implementing logic
 * related to the reimbursements in the database.
 * @author Justin W
 *
 */
public interface ErsReimbursementService {
	/**
	 * Used for submitting a new reimbursement into the system
	 * @param amount the amount on the reimbursement
	 * @param desc the reimbursement description
	 * @param authUser the user submitting the reimbursement
	 * @param type the type of reimbursement
	 * @return whether the submission is valid i.e. amount is 
	 * not negative, description isn't empty, etc
	 */
	public boolean submitReimbursement(double amount, String desc, int authUser, ReimbType type);
	/**
	 * Approves a given reimbursement through it's id and checking a user's credentials
	 * @param reimbId the ID of the reimbursement to approve
	 * @param userId the user ID to check that is trying to approve the reimbursement
	 */
	public void approveReimbursement(int reimbId, int userId);
	/**
	 * Denies a given reimbursement through it's id and checking a user's credentials
	 * @param reimbId the ID of the reimbursement to approve
	 * @param userId the user ID to check that is trying to approve the reimbursement
	 */
	public void disapproveReimbursement(int reimbId, int userId);
	/**
	 * Gets all of the reimbursements associated to a given user's id
	 * @param userID the id of the user who's reimbursements are being looked up
	 * @return a List containing all the reimbursements of the user
	 */
	public List<Reimbursement> getAllUserReimbursements(int userID);
	/**
	 * Gets every reimbursement with a status of PENDING in the database
	 * @return a List containing all of the PENDING reimbursements
	 */
	public List<Reimbursement> getAllPendingReimbursements();
	/**
	 * Gets every reimbursement in the database
	 * @return a List containing every reimbursement in the system
	 */
	public List<Reimbursement> getAllReimbursementHistory();
}
