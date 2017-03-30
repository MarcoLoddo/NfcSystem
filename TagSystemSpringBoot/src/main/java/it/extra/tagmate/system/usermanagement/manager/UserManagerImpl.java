package it.extra.tagmate.system.usermanagement.manager;

import java.util.List;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.extra.tagmate.system.usermanagement.dao.NfcDao;
import it.extra.tagmate.system.usermanagement.dao.UserDao;
import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;

@Component
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserDao userDao;
	@Autowired
	private NfcDao nfcDao;

	@Transactional
	public UserEntity findUser(UserEntity user) {
		return userDao.findByEmailPassword(user.getEmail(), user.getPassword());
	}

	@Transactional
	public List<UserEntity> userListByName(String name) {
		return userDao.findUserByName(name);
	}

	@Transactional
	public UserEntity updateUser(UserEntity user) {
		
		List<NfcTagEntity> dbUserNfc = nfcDao.findByUser(user);
		if (dbUserNfc.size() < user.getNfcTags().size()) {
			for (NfcTagEntity nfc : user.getNfcTags()) {
				if (!dbUserNfc.contains(nfc))
					nfc.setUser(user);
					nfcDao.save(nfc);
			}
		}
		// update user info
		userDao.save(user);

		return userDao.findById(user.getUserId());
	}

	@Transactional
	public UserEntity findById(int id) {
		UserEntity userEntity = userDao.findById(id);
		return userEntity;
	}

	@Override
	@Transactional
	public List<NfcTagEntity> findNfcOfUser(UserEntity user) {
		return nfcDao.findByUser(user);
	}

}
