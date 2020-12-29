package model;

/**
 * The class to represent a user in Java
 * contains all the same aspects as a user
 * in the database joined onto its lookup table 
 * @author Justin W
 *
 */
public class User {
	
	/**
	 * An enum representing the user roles
	 * EMPLOYEE, FINANCE_M
	 */
	public enum Role{
		EMPLOYEE,
		FINANCE_M
	}
	
	private int userId;
	private String uUsername;
	private String uPassword;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int roleID;
	private Role role;
	
	public User() {
	}

	public User(int userId, String uUsername, String uPassword, String firstName, String lastName, String emailAddress,
			int roleID, Role role) {
		this.userId = userId;
		this.uUsername = uUsername;
		this.uPassword = uPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.roleID = roleID;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getuUsername() {
		return uUsername;
	}

	public void setuUsername(String uUsername) {
		this.uUsername = uUsername;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", uUsername=" + uUsername + ", uPassword=" + uPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", roleID=" + roleID
				+ ", role=" + role + "]";
	}
	
}
