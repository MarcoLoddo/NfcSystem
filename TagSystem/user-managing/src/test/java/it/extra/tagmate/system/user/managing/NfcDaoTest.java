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

import it.extra.tagmate.system.user.management.entity.NfcTagEntity;
import it.extra.tagmate.system.user.management.entity.UserEntity;
import it.extra.tagmate.system.user.management.dao.NfcDao;
import it.extra.tagmate.system.user.management.dao.UserDao;


 
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class NfcDaoTest {
	@Autowired
	private NfcDao nfcDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testRead() 
	{
		assertTrue(nfcDao.getByStatus(true).size()==3);
		UserEntity userTest=userDao.getByUserPassword("super@man", "clarkent");
		assertTrue(nfcDao.getByUser(userTest).size()==2);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void disabling()
	{
		UserEntity userEntity=userDao.getByUserPassword("super@man", "clarkent");
		List<NfcTagEntity> nfcTagEntities=nfcDao.getByUser(userEntity);
		for (NfcTagEntity nfcTagEntity : nfcTagEntities) {
			nfcTagEntity.setDisabled(true);
		}
		
		nfcDao.update(nfcTagEntities);
		nfcTagEntities=nfcDao.getByUser(userEntity);
		int count=0;
		for (NfcTagEntity nfcTagEntity : nfcTagEntities) {
			if(nfcTagEntity.isDisabled())
				count++;
		}
		assertTrue(count==2);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void updatingNfc()
	{
		UserEntity userTest=userDao.getByUserPassword("super@man", "clarkent");
		NfcTagEntity nfcTagEntity=new NfcTagEntity();
		nfcTagEntity.setNfcId("prova");
		nfcTagEntity.setUser(userTest);
		nfcDao.insertNew(nfcTagEntity);
		userTest.getNfc().add(nfcTagEntity);
		assertTrue(userTest.getNfc().size()==nfcDao.getByUser(userTest).size());
	}
}
