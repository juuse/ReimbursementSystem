package project1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ErsReimbursementDao;
import dao.ErsReimbursementDaoImpl;
import dao.ErsUsersDao;
import dao.ErsUsersDaoImpl;
import service.ErsUserService;
import service.ErsUsersServiceImpl;

public class UserServiceTest {
	
	private static ErsUsersDao usersDao;
	private static ErsReimbursementDao reimbDao;
	private static ErsUserService userServ;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usersDao = new ErsUsersDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
		reimbDao = new ErsReimbursementDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
		userServ = new ErsUsersServiceImpl(usersDao, reimbDao);
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
	public void testVerifyLoginCredentials() {
		assertTrue(userServ.verifyLoginCredentials("someusername", "somepass"));
		assertFalse(userServ.verifyLoginCredentials("null", "null"));
		assertFalse(userServ.verifyLoginCredentials(null, null));
	}

	@Test
	public void testGetUserByCredentials() {
		assertNotNull(userServ.getUserByCredentials("userman", "passman"));
		assertNull(userServ.getUserByCredentials("randomuser", "thebiggestpass12323232131456754"));
		assertNull(userServ.getUserByCredentials(null, null));
	}

	@Test
	public void testGetAllReimbursements() {
		assertEquals(2, userServ.getAllReimbursements(userServ.getUserByCredentials("someusername", "somepass")).size());
		assertEquals(1, userServ.getAllReimbursements(userServ.getUserByCredentials("userman", "passman")).size());
		assertNull(userServ.getAllReimbursements(null));
	}

	@Test
	public void testGetNameIDMap() {
		Map<Integer, String> idmap = userServ.getNameIDMap();
		assertEquals("Billy Maze", idmap.get(1));
		assertEquals("Jill Jane", idmap.get(3));
		assertFalse(idmap.containsKey(5));
	}

}
