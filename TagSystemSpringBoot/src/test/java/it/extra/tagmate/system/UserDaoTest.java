package it.extra.tagmate.system;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import it.extra.tagmate.system.usermanagement.dao.NfcDao;
import it.extra.tagmate.system.usermanagement.dao.UserDao;
import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	@Autowired
	private NfcDao nfcDao;
	@Test
	@Transactional
	public void testRead()
	{
		List<UserEntity> users;
		users=userDao.findUserByName("bob");
		assertTrue(users.size() == 4);

		users=userDao.findUserByName("rob");
		assertTrue(users.size() == 1);
		UserEntity nullUser=userDao.findByEmailPassword("super@man", "clarkent1");
		assertTrue(nullUser==null);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testWrite()
	{
		UserEntity u1=new UserEntity();
		u1.setFirstName("Carl");
		u1.setLastName("Carlson");
		u1.setEmail("carl@carlson");
		u1.setPassword("superman");
		System.out.print(u1);
		userDao.save(u1);
	}
	@Test
	@Transactional
	public void recoverByTag()
	{
		NfcTagEntity nfcTagEntity=nfcDao.getByTag("7");
		UserEntity userEntity=userDao.getUserByNfc(nfcTagEntity);
		assertTrue(userEntity!=null);
	}
	@Test 
	public void login()
	{
		UserEntity userEntity=userDao.findByEmailPassword("super@man", "clarkent");
		assertTrue(userEntity!=null);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void updateUser()
	{
		UserEntity userEntity = userDao.findByEmailPassword("super@man", "clarkent");
		String nameBefore=userEntity.getFirstName();
		userEntity.setFirstName("Clark");
		userDao.save(userEntity);
		userEntity = userDao.findByEmailPassword("super@man", "clarkent");
		String nameAfter=userEntity.getFirstName();
		assertTrue(nameBefore!=nameAfter);
	}
}
