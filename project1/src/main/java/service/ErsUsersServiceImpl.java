package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ErsReimbursementDao;
import dao.ErsReimbursementDaoImpl;
import dao.ErsUsersDao;
import dao.ErsUsersDaoImpl;
import model.Reimbursement;
import model.User;

public class ErsUsersServiceImpl implements ErsUserService {

	private ErsUsersDao userDao;
	private ErsReimbursementDao reimbDao;
	
	public ErsUsersServiceImpl() {
		userDao = new ErsUsersDaoImpl();
		reimbDao = new ErsReimbursementDaoImpl();
	}
	
	public ErsUsersServiceImpl(ErsUsersDao dao, ErsReimbursementDao dao2) {
		userDao = dao;
		reimbDao = dao2;
	}
	
	@Override
	public boolean verifyLoginCredentials(String name, String pass) {
		User u = userDao.selectUserByUserPass(name, pass);
		return u != null;
	}
	
	@Override
	public User getUserByCredentials(String name, String pass) {
		return userDao.selectUserByUserPass(name, pass);
	}

	@Override
	public List<Reimbursement> getAllReimbursements(User u) {
		if(u == null) return null;
		return reimbDao.selectAllByAuthor(u.getUserId());
	}

	@Override
	public Map<Integer, String> getNameIDMap() {
		Map<Integer, String> idUser = new HashMap<>();
		List<User> allUsers = userDao.selectAllUsers();
		
		for(User u: allUsers) {
			idUser.put(u.getUserId(), u.getFirstName() + " " + u.getLastName());
		}
		
		return idUser;
	}
	
	public String[] getNameIDArray() {
		
		List<User> allUsers = userDao.selectAllUsers();
		
		String[] allNames = new String[allUsers.size() + 20];
		
		for(User u: allUsers) {
			allNames[u.getUserId()] = u.getFirstName() + " " + u.getLastName();
		}
		
		return allNames;
	}

}
