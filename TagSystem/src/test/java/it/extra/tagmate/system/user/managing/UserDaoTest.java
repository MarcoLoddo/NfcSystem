package it.extra.tagmate.system.user.managing;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import it.extra.tagmate.system.user.management.dao.UserDao;
import it.extra.tagmate.system.user.management.entity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	private UserDao userDao;
	@Test
	@Transactional
	public void testRead()
	{
		List<UserEntity> users;
		users=userDao.findByName("bob");
		assertTrue(users.size() == 4);

		users=userDao.findByName("rob");
		assertTrue(users.size() == 1);
		UserEntity nullUser=userDao.findByEmailPassword("super@man", "clarkent1");
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
			userDao.save(u1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	/*@Test
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
	}*/

}
