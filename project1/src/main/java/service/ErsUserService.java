package service;

import java.util.List;
import java.util.Map;

import model.Reimbursement;
import model.User;

/**
 * The service layer in charge of implementing logic
 * related to the Users in the database.
 * @author Justin W
 *
 */
public interface ErsUserService {
	/**
	 * Checks whether the user credentials are valid
	 * @param name the entered username
	 * @param pass the entered password
	 * @return whether the set of credentials exists
	 */
	public boolean verifyLoginCredentials(String name, String pass);
	/**
	 * Gets a user from the database based on the entered credentials
	 * @param name the attached username
	 * @param pass the attached password
	 * @return the user's information with the matching username and password
	 */
	public User getUserByCredentials(String name, String pass);
	/**
	 * Gets all reimbursements associated to the user given
	 * @param u the user that reimbursements are wanted from
	 * @return a List containing every reimbursement of the user provided
	 */
	public List<Reimbursement> getAllReimbursements(User u);
	/**
	 * Looks in the database and gets a map of every user's ID and their name
	 * @return a Map with the key being the user ID and the value associated is the name
	 */
	public Map<Integer, String> getNameIDMap();
	/**
	 * Puts user names from the database to an array in the index 
	 * matching their ID number
	 * @return an Array with the name in the spot of it's id number
	 */
	public String[] getNameIDArray();
}
