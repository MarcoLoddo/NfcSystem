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

import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.NfcDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.UserDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	@Autowired
	private NfcDao nfcDao;
	
	
	@Test
	@Transactional
	public void testwrite()
	{
		//write new nfc, pickup from user the update list
		
	}
	
}
