package project1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ErsUsersDao;
import dao.ErsUsersDaoImpl;
import model.User.Role;

public class UsersDaoTest {
	
	private static ErsUsersDao usersDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usersDao = new ErsUsersDaoImpl("jdbc:h2:./testDBFolder/testData", "sa", "sa");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		usersDao.h2InitDao();
	}

	@After
	public void tearDown() throws Exception {
		usersDao.h2DestroyDao();
	}

	@Test
	public void testSelectAllUsers() {
		assertEquals(3, usersDao.selectAllUsers().size());
	}

	@Test
	public void testSelectAllUsersByRole() {
		assertEquals(2, usersDao.selectAllUsersByRole(Role.EMPLOYEE).size());
		assertEquals(1, usersDao.selectAllUsersByRole(Role.FINANCE_M).size());
	}

	@Test
	public void testSelectUserById() {
		assertEquals("someusername", usersDao.selectUserById(1).getuUsername());
		assertEquals(Role.FINANCE_M, usersDao.selectUserById(3).getRole());
	}

	@Test
	public void testSelectUserByUserPass() {
		assertEquals("Billy", usersDao.selectUserByUserPass("someusername", "somepass").getFirstName());
		assertNull(usersDao.selectUserByUserPass("a", "b"));
	}

}
