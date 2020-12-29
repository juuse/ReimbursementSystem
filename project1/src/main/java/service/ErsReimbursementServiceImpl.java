package service;

import java.sql.Timestamp;
import java.util.List;

import dao.ErsReimbursementDao;
import dao.ErsReimbursementDaoImpl;
import dao.ErsUsersDao;
import dao.ErsUsersDaoImpl;
import model.Reimbursement;
import model.Reimbursement.ReimbStatus;
import model.Reimbursement.ReimbType;
import model.User.Role;

public class ErsReimbursementServiceImpl implements ErsReimbursementService {

	private ErsUsersDao userDao = new ErsUsersDaoImpl();
	private ErsReimbursementDao reimbDao = new ErsReimbursementDaoImpl();
	
	public ErsReimbursementServiceImpl(){
	}
	
	public ErsReimbursementServiceImpl(ErsUsersDao dao1, ErsReimbursementDao dao2){
		userDao = dao1;
		reimbDao = dao2;
	}
	
	@Override
	public boolean submitReimbursement(double amount, String desc, int authUser, ReimbType type) {
		if(amount <= 0 || desc.isEmpty() || desc == null || authUser <= 0)
			return false;
		
		switch(type) {
			case LODGING:
				reimbDao.insertReimbursement(amount, desc, authUser, 1);
				return true;
			case TRAVEL:
				reimbDao.insertReimbursement(amount, desc, authUser, 2);
				return true;
			case FOOD:
				reimbDao.insertReimbursement(amount, desc, authUser, 3);
				return true;
			case OTHER:
				reimbDao.insertReimbursement(amount, desc, authUser, 4);
				return true;
			default:
				return false;
		}
	}

	@Override
	public void approveReimbursement(int reimbId, int userId) {
		if(userDao.selectUserById(userId) != null && userDao.selectUserById(userId).getRole() == Role.FINANCE_M) {
			Reimbursement modify = reimbDao.selectReimbursementById(reimbId);
			if(modify != null) {
				modify.setReimbResolver(userId);
				modify.setReimbResolved(new Timestamp(System.currentTimeMillis()));
				modify.setReimbStatusID(2);
				reimbDao.updateReimbursement(modify);
			}
		}
	}

	@Override
	public void disapproveReimbursement(int reimbId, int userId) {
		if(userDao.selectUserById(userId) != null && userDao.selectUserById(userId).getRole() == Role.FINANCE_M) {
			Reimbursement modify = reimbDao.selectReimbursementById(reimbId);
			if(modify != null) {
				modify.setReimbResolver(userId);
				modify.setReimbResolved(new Timestamp(System.currentTimeMillis()));
				modify.setReimbStatusID(3);
				reimbDao.updateReimbursement(modify);
			}
		}
	}

	@Override
	public List<Reimbursement> getAllUserReimbursements(int userID) {
		return reimbDao.selectAllByAuthor(userID);
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() {
		return reimbDao.selectAllByStatus(ReimbStatus.PENDING);
	}

	@Override
	public List<Reimbursement> getAllReimbursementHistory() {
		return reimbDao.selectAllReimbursements();
	}

}
