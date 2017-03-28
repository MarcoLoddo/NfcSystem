package it.extra.tagmate.system.usermanagement.manager;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.extra.tagmate.system.usermanagement.dao.NfcDao;
import it.extra.tagmate.system.usermanagement.dao.UserDao;
import it.extra.tagmate.system.usermanagement.data.NfcEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;
@Component
public class UserManagerImpl implements UserManager {
	@Autowired private UserDao userDao;
	@Autowired private NfcDao nfcDao;
	
	@Transactional
	public UserEntity login(UserEntity user)
	{
		return userDao.findByEmailPassword(user.getEmail(), user.getPassword());
	}
	@Transactional
	public List<UserEntity> userListByName(String name)
	{
		return userDao.findUserByName(name);
	}
	@Transactional
	public void saveUser(UserEntity user)
	{
		userDao.save(user);
	}
	@Transactional
	public void saveNfc(NfcEntity nfc)
	{
		nfcDao.save(nfc);
	}

}
