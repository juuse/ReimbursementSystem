package project1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ErsReimbursementDao;
import dao.ErsReimbursementDaoImpl;
import dao.ErsUsersDao;
import dao.ErsUsersDaoImpl;
import model.Reimbursement.ReimbStatus;
import model.Reimbursement.ReimbType;
import service.ErsReimbursementService;
import service.ErsReimbursementServiceImpl;

public class ReimbursementServiceTest {
	
	private static ErsUsersDao usersDao;
	private static ErsReimbursementDao reimbDao;
	private static ErsReimbursementService reimbServ;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usersDao = new ErsUsersDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
		reimbDao = new ErsReimbursementDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
		reimbServ = new ErsReimbursementServiceImpl(usersDao, reimbDao);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		usersDao.h2InitDao();
		reimbDao.h2InitDao();
	}

	@After
	public void tearDown() throws Exception {
		reimbDao.h2DestroyDao();
		usersDao.h2DestroyDao();
	}

	@Test
	public void testSubmitReimbursement() {
		assertEquals(3, reimbServ.getAllReimbursementHistory().size());
		assertTrue(reimbServ.submitReimbursement(20.2, "random testing", 1, ReimbType.FOOD));
		assertFalse(reimbServ.submitReimbursement(-25.2, "random testing 2", 2, ReimbType.LODGING));
		assertEquals(4, reimbServ.getAllReimbursementHistory().size());
	}

	@Test
	public void testApproveReimbursement() {
		assertEquals(ReimbStatus.PENDING, reimbDao.selectReimbursementById(1).getrStatus());
		reimbServ.approveReimbursement(1, 3);
		assertEquals(ReimbStatus.APPROVED, reimbDao.selectReimbursementById(1).getrStatus());
	}

	@Test
	public void testDisapproveReimbursement() {
		assertEquals(ReimbStatus.PENDING, reimbDao.selectReimbursementById(1).getrStatus());
		reimbServ.disapproveReimbursement(1, 3);
		assertEquals(ReimbStatus.DENIED, reimbDao.selectReimbursementById(1).getrStatus());
	}

	@Test
	public void testGetAllUserReimbursements() {
		assertEquals(2, reimbServ.getAllUserReimbursements(1).size());
		assertEquals(1, reimbServ.getAllUserReimbursements(2).size());
		assertEquals(0, reimbServ.getAllUserReimbursements(5).size());
	}

	@Test
	public void testGetAllPendingReimbursements() {
		assertEquals(1, reimbServ.getAllPendingReimbursements().size());
	}

	@Test
	public void testGetAllReimbursementHistory() {
		assertEquals(3, reimbServ.getAllReimbursementHistory().size());
	}

}
