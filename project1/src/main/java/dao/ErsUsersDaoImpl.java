package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.User.Role;

public class ErsUsersDaoImpl implements ErsUsersDao {

	public String url;
	public String username;
	public String password;
	
	static {
        try {
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Static block has failed me");
        }
    }
	
	public ErsUsersDaoImpl() {
		url = "jdbc:postgresql://"+ System.getenv("TRAINING_DB") + "/reimbursementsystem";
		username = System.getenv("TRAINING_DB_USERNAME");
		password = System.getenv("TRAINING_PASSWORD");
	}
	
	public ErsUsersDaoImpl(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_users inner join ers_user_roles on ers_users.user_role_id = ers_user_role_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), Role.valueOf(rs.getString("user_role"))));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public List<User> selectAllUsersByRole(Role role) {
		List<User> users = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_users inner join ers_user_roles on ers_users.user_role_id = ers_user_role_id"
					+ " where user_role = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, role.toString());
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), Role.valueOf(rs.getString("user_role"))));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User selectUserById(int userID) {
		User user = null;
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_users inner join ers_user_roles on ers_users.user_role_id = ers_user_role_id"
					+ " where ers_users_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userID);
			
			ResultSet rs = ps.executeQuery(); 
			
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), Role.valueOf(rs.getString("user_role")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User selectUserByUserPass(String uUsername, String uPassword) {
		User user = null;
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_users inner join ers_user_roles on ers_users.user_role_id = ers_user_role_id"
					+ " where ers_username = ? and ers_password = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, uUsername);
			ps.setString(2, uPassword);
			
			ResultSet rs = ps.executeQuery(); 
			
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), Role.valueOf(rs.getString("user_role")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void updateUser(User u) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "UPDATE ers_users SET ers_username = ?, ers_password = ?, user_first_name = ?, "
					+ "user_last_name = ?, user_email = ?, user_role_id = ?"
					+ "WHERE ers_users_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, u.getuUsername());
			ps.setString(2, u.getuPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmailAddress());
			ps.setInt(6, u.getRoleID());
			ps.setInt(7, u.getUserId());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void h2InitDao() {
		try(Connection conn=
				DriverManager.getConnection(url,username, password))
		{
			String sql= ""+
				"create table ers_user_roles(\r\n" + 
				"ers_user_role_id integer\r\n" + 
				", user_role varchar(10) not null\r\n" + 
				", primary key (ers_user_role_id)\r\n" + 
				"); " +
				"create table ers_users(\r\n" + 
				"ers_users_id serial\r\n" + 
				", ers_username varchar(50) not null unique\r\n" + 
				", ers_password varchar(50) not null\r\n" + 
				", user_first_name varchar(100) not null\r\n" + 
				", user_last_name varchar(100) not null\r\n" + 
				", user_email varchar(150) not null unique\r\n" + 
				", user_role_id integer not null\r\n" + 
				", primary key (ers_users_id)\r\n" + 
				", foreign key (user_role_id) references ers_user_roles(ers_user_role_id)\r\n" + 
				"); "+
					"insert into ers_user_roles values (1, 'EMPLOYEE');\r\n" + 
					"insert into ers_user_roles values (2, 'FINANCE_M');" + 
					"insert into ers_users values(default, 'someusername', 'somepass', 'Billy', 'Maze', 'bmaze@yahoo.com', 1);" + 
					"insert into ers_users values(default, 'userman', 'passman', 'He', 'Man', 'heyay@yahoo.com', 1);" + 
					"insert into ers_users values(default, 'hello123', 'secret234', 'Jill', 'Jane', 'jijane@gmail.com', 2);";
			
			Statement state = conn.createStatement();
			state.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void h2DestroyDao() {
		try(Connection conn=
				DriverManager.getConnection(url,username, password))
		{
			String sql= ""+
			"DROP TABLE ers_users;"+
			"DROP TABLE ers_user_roles;";
			
			Statement state = conn.createStatement();
			state.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
