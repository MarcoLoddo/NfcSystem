package it.extra.tagmate.system.user.managing;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.extra.tagmate.system.user.management.dao.NfcDao;
import it.extra.tagmate.system.user.management.dao.UserDao;
import it.extra.tagmate.system.user.management.entity.NfcTagEntity;
import it.extra.tagmate.system.user.management.entity.UserEntity;
import org.hibernate.cfg.Configuration;

 
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
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
		users=userDao.getUsersByName("bob");
		assertTrue(users.size() == 4);

		users=userDao.getUsersByName("rob");
		assertTrue(users.size() == 1);
		UserEntity nullUser=userDao.getByUserPassword("super@man", "clarkent1");
		assertTrue(nullUser==null);
	}
	@Test
	@Transactional
	@Rollback(false)
	public void testWrite()
	{
		
		try
		{
			UserEntity u1=new UserEntity();
			u1.setFirstName("Carl");
			u1.setLastName("Carlson");
			u1.setEmail("carl@carlson");
			u1.setPassword("superman");
			userDao.insertNew(u1);
			userDao.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	@Test
	public void recoverByTag()
	{
		NfcTagEntity nfcTagEntity=nfcDao.getByTag("6");
		UserEntity userEntity=userDao.getUserByNfc(nfcTagEntity);
		assertTrue(userEntity!=null);
	}
	@Test 
	public void login()
	{
		assertTrue(userDao.getByUserPassword("super@man", "clarkent")!=null);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void updateUser()
	{
		UserEntity userEntity = userDao.getByUserPassword("super@man", "clarkent");
		String nameBefore=userEntity.getFirstName();
		userEntity.setFirstName("Clark");
		userDao.update(userEntity);
		userEntity = userDao.getByUserPassword("super@man", "clarkent");
		String nameAfter=userEntity.getFirstName();
		assertTrue(nameBefore!=nameAfter);
	}

}
