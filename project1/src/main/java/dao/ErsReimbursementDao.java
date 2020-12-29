package dao;

import java.util.List;

import model.Reimbursement;
import model.Reimbursement.ReimbStatus;
import model.Reimbursement.ReimbType;

/**
 * Dao layer class for accessing the reimbursement table in the database
 * @author Justin W
 *
 */
public interface ErsReimbursementDao {
	
	/**
	 * Adds a new reimbursement to the table in the database
	 * @param amount The amount of the reimbursement to add
	 * @param desc The description of the reimbursement to add
	 * @param authUser The user authoring the reimbursement
	 * @param type The type of the reimbursement to add
	 */
	public void insertReimbursement(double amount, String desc, int authUser, int type);
	/**
	 * Selects a reimbursement from the table by an ID
	 * @param reimbID The id to query the able by
	 * @return The reimbursement information associated to the given ID
	 */
	public Reimbursement selectReimbursementById(int reimbID);
	/**
	 * Selects every reimbursement in the table
	 * @return A List that contains the reimbursement information for every 
	 * reimbursement in the database
	 */
	public List<Reimbursement> selectAllReimbursements();
	/**
	 * Selects every reimbursement of a given type from the database
	 * @param reimbType The type to query with in the table LODGING FOOD TRAVEL OTHER
	 * @return A List that contains every reimbursement associated to the given type
	 */
	public List<Reimbursement> selectAllByType(ReimbType reimbType);
	/**
	 * Selects every reimbursement of a given status from the database
	 * @param reimbStat The status to query with in the table. PENDING DENIED APPROVED
	 * @return A List that contains every reimbursement associated to the given status
	 */
	public List<Reimbursement> selectAllByStatus(ReimbStatus reimbStat);
	/**
	 * Selects every reimbursement authored by a certain ID 
	 * @param authorID the author ID to get all reimbursements of
	 * @return A List that contains every reimbursement associated to the given author
	 */
	public List<Reimbursement> selectAllByAuthor(int authorID);
	/**
	 * Updates a reimbursement in the table given the information
	 * @param reimb The reimbursement information used to update the information in the table
	 */
	public void updateReimbursement(Reimbursement reimb);
	
	public void h2InitDao();
	public void h2DestroyDao();
}
