package it.extra.tagmate.system.usermanagement.manager;

import java.util.ArrayList;
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
	public void updateUser(UserEntity user) {
		if (user.getNfcTags() != null) {
			List<NfcTagEntity> dbUserNfc = nfcDao.findByUser(user);
			if (dbUserNfc.size() < user.getNfcTags().size())
				for (NfcTagEntity nfc : user.getNfcTags())
					if (!dbUserNfc.contains(nfc)) {
						nfc.setUser(user);
						nfcDao.save(nfc);
					}

		} else
			//declaration in case of null collection to prevent hibernate from not fetching the list
			//if the collection is not declared, hibernate can't put in the data because
			//list is an abstract type which hasn't a unique implementation
			user.setNfcTags(new ArrayList<NfcTagEntity>());
		userDao.save(user);
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
