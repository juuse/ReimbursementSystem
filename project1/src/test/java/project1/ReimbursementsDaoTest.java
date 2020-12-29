package project1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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

public class ReimbursementsDaoTest {
	
	private static ErsUsersDao usersDao;
	private static ErsReimbursementDao reimbDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usersDao = new ErsUsersDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
		reimbDao = new ErsReimbursementDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
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
	public void testSelectReimbursementById() {
		assertEquals("Went to japan", reimbDao.selectReimbursementById(2).getReimbDescription());
		assertEquals(ReimbType.LODGING, reimbDao.selectReimbursementById(1).getrType());
		assertNull(reimbDao.selectReimbursementById(6));
	}

	@Test
	public void testSelectAllReimbursements() {
		assertEquals(3, reimbDao.selectAllReimbursements().size());
	}

	@Test
	public void testSelectAllByType() {
		assertEquals(1, reimbDao.selectAllByType(ReimbType.LODGING).size());
		assertEquals(1, reimbDao.selectAllByType(ReimbType.FOOD).size());
		assertEquals(1, reimbDao.selectAllByType(ReimbType.TRAVEL).size());
		assertEquals(0, reimbDao.selectAllByType(ReimbType.OTHER).size());
	}

	@Test
	public void testSelectAllByStatus() {
		assertEquals(1, reimbDao.selectAllByStatus(ReimbStatus.PENDING).size());
		assertEquals(1, reimbDao.selectAllByStatus(ReimbStatus.DENIED).size());
		assertEquals(1, reimbDao.selectAllByStatus(ReimbStatus.APPROVED).size());
	}

	@Test
	public void testSelectAllByAuthor() {
		assertEquals(2, reimbDao.selectAllByAuthor(1).size());
		assertEquals(1, reimbDao.selectAllByAuthor(2).size());
	}

}
