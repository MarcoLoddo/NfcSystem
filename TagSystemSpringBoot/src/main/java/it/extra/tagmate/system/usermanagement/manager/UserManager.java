package it.extra.tagmate.system.usermanagement.manager;

import java.util.List;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;

public interface UserManager {
	UserEntity findUser(UserEntity user);
	UserEntity findById(int id);
	List<UserEntity> userListByName(String name);
	void updateUser(UserEntity user);
	List<NfcTagEntity> findNfcOfUser(UserEntity user);
}
