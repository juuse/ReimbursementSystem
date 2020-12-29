package dao;

import java.util.List;

import model.User;
import model.User.Role;

/**
 * Dao layer class for accessing the user table in the database
 * @author Justin W
 *
 */
public interface ErsUsersDao {
	
	/**
	 * Selects every user and their information from the database
	 * @return every user and their information from the database
	 */
	public List<User> selectAllUsers();
	/**
	 * Selects all users using their role to query from the database
	 * @param role the role of the user to query by
	 * @return All users with the same role as entered
	 */
	public List<User> selectAllUsersByRole(Role role);
	/**
	 * Selects a user by their ID from the database
	 * @param userID the ID of the user to look up
	 * @return the user's information associated to the ID
	 */
	public User selectUserById(int userID);
	/**
	 * Selects the users information by their username and password
	 * @param uUsername the username to query the database with
	 * @param uPassword the password to query the database with
	 * @return the User's information associated with the given username and password
	 */
	public User selectUserByUserPass(String uUsername, String uPassword);
	/**
	 * Updates a user's information in the database
	 * @param u the user with every parameter to keep or change
	 */
	public void updateUser(User u);
	
	public void h2InitDao();
	public void h2DestroyDao();
}
