package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reimbursement;
import model.Reimbursement.ReimbStatus;
import model.Reimbursement.ReimbType;

public class ErsReimbursementDaoImpl implements ErsReimbursementDao {
	
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
	
	public ErsReimbursementDaoImpl() {
		url = "jdbc:postgresql://"+ System.getenv("TRAINING_DB") + "/reimbursementsystem";
		username = System.getenv("TRAINING_DB_USERNAME");
		password = System.getenv("TRAINING_PASSWORD");
	}
	
	public ErsReimbursementDaoImpl(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public void insertReimbursement(double amount, String desc, int authUser, int type) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "insert into ers_reimbursement values"
					+ "(default, ?, CURRENT_TIMESTAMP, null, ?, ?, null, 1, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, amount);
			ps.setString(2, desc);
			ps.setInt(3, authUser);
			ps.setInt(4, type);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Reimbursement selectReimbursementById(int reimbID) {
		Reimbursement reimb = null;
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_reimbursement r inner join ers_reimbursement_status rs on "
					+ "r.reimb_status_id = rs.reimb_status_id inner join ers_reimbursement_type rt on "
					+ "r.reimb_type_id = rt.reimb_type_id where reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reimbID);
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				reimb = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						ReimbStatus.valueOf(rs.getString("reimb_status")), ReimbType.valueOf(rs.getString("reimb_type")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}
	
	@Override
	public List<Reimbursement> selectAllReimbursements() {
		List<Reimbursement> reimbs = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_reimbursement r inner join ers_reimbursement_status rs on "
					+ "r.reimb_status_id = rs.reimb_status_id inner join ers_reimbursement_type rt on "
					+ "r.reimb_type_id = rt.reimb_type_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						ReimbStatus.valueOf(rs.getString("reimb_status")), ReimbType.valueOf(rs.getString("reimb_type"))));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public List<Reimbursement> selectAllByType(ReimbType reimbType) {
		List<Reimbursement> reimbs = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_reimbursement r inner join ers_reimbursement_status rs on "
					+ "r.reimb_status_id = rs.reimb_status_id inner join ers_reimbursement_type rt on "
					+ "r.reimb_type_id = rt.reimb_type_id where reimb_type = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, reimbType.toString());
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						ReimbStatus.valueOf(rs.getString("reimb_status")), ReimbType.valueOf(rs.getString("reimb_type"))));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public List<Reimbursement> selectAllByStatus(ReimbStatus reimbStat) {
		List<Reimbursement> reimbs = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_reimbursement r inner join ers_reimbursement_status rs on "
					+ "r.reimb_status_id = rs.reimb_status_id inner join ers_reimbursement_type rt on "
					+ "r.reimb_type_id = rt.reimb_type_id where reimb_status = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, reimbStat.toString());
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						ReimbStatus.valueOf(rs.getString("reimb_status")), ReimbType.valueOf(rs.getString("reimb_type"))));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public List<Reimbursement> selectAllByAuthor(int authorID) {
		List<Reimbursement> reimbs = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "select * from ers_reimbursement r inner join ers_reimbursement_status rs on "
					+ "r.reimb_status_id = rs.reimb_status_id inner join ers_reimbursement_type rt on "
					+ "r.reimb_type_id = rt.reimb_type_id where reimb_author = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, authorID);
			
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				reimbs.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						ReimbStatus.valueOf(rs.getString("reimb_status")), ReimbType.valueOf(rs.getString("reimb_type"))));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public void updateReimbursement(Reimbursement reimb) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "UPDATE ers_reimbursement SET reimb_amount = ?, reimb_submitted = ?, reimb_resolved = ?, "
					+ "reimb_description = ?, reimb_author = ?, reimb_resolver = ?, "
					+ "reimb_status_id = ?, reimb_type_id = ?"
					+ "WHERE reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, reimb.getReimbAmount());
			ps.setTimestamp(2, reimb.getReimbSubmitted());
			ps.setTimestamp(3, reimb.getReimbResolved());
			ps.setString(4, reimb.getReimbDescription());
			ps.setInt(5, reimb.getReimbAuthor());
			ps.setInt(6, reimb.getReimbResolver());
			ps.setInt(7, reimb.getReimbStatusID());
			ps.setInt(8, reimb.getReimbTypeID());
			ps.setInt(9, reimb.getReimbID());
			
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
				"create table ers_reimbursement_type(\r\n" + 
				"reimb_type_id integer\r\n" + 
				", reimb_type varchar(10) not null\r\n" + 
				", primary key (reimb_type_id)\r\n" + 
				");" +
				"create table ers_reimbursement_status(\r\n" + 
				"reimb_status_id integer\r\n" + 
				", reimb_status varchar(10) not null\r\n" + 
				", primary key (reimb_status_id)\r\n" + 
				");" +
				"create table ers_reimbursement(\r\n" + 
				"reimb_id serial\r\n" + 
				", reimb_amount decimal(20, 2) not null\r\n" + 
				", reimb_submitted timestamp not null\r\n" + 
				", reimb_resolved timestamp\r\n" + 
				", reimb_description varchar(250)\r\n" + 
				", reimb_author integer not null\r\n" + 
				", reimb_resolver integer\r\n" + 
				", reimb_status_id integer not null\r\n" + 
				", reimb_type_id integer not null\r\n" + 
				", primary key (reimb_id)\r\n" + 
				", foreign key (reimb_author) references ers_users(ers_users_id)\r\n" + 
				", foreign key (reimb_resolver) references ers_users(ers_users_id)\r\n" + 
				", foreign key (reimb_status_id) references ers_reimbursement_status(reimb_status_id)\r\n" + 
				", foreign key (reimb_type_id) references ers_reimbursement_type(reimb_type_id)\r\n" + 
				");"+
					"insert into ers_reimbursement_type values (1, 'LODGING');\r\n" + 
					"insert into ers_reimbursement_type values (2, 'TRAVEL');\r\n" + 
					"insert into ers_reimbursement_type values (3, 'FOOD');\r\n" + 
					"insert into ers_reimbursement_type values (4, 'OTHER');\r\n" + 
					"insert into ers_reimbursement_status values (1, 'PENDING');\r\n" + 
					"insert into ers_reimbursement_status values (2, 'APPROVED');\r\n" + 
					"insert into ers_reimbursement_status values (3, 'DENIED');" + 
					"insert into ers_reimbursement values(default, 20.5, CURRENT_TIMESTAMP, null, 'Doing whatever right now', 1, null, 1, 1);" + 
					"insert into ers_reimbursement values(default, 543.32, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Went to japan', 1, 3, 2, 2);" + 
					"insert into ers_reimbursement values(default, 133.43, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'party at applebees', 2, 3, 3, 3);";
			
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
			"DROP TABLE ers_reimbursement;"+
			"DROP TABLE ers_reimbursement_status;"+
			"DROP TABLE ers_reimbursement_type;";
			
			Statement state = conn.createStatement();
			state.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
