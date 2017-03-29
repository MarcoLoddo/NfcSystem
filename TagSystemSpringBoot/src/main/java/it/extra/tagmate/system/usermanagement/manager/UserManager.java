package it.extra.tagmate.system.usermanagement.manager;

import java.util.List;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;

public interface UserManager {
	UserEntity login(UserEntity user);
	List<UserEntity> userListByName(String name);
	void saveUser(UserEntity user);
	void saveNfc(NfcTagEntity nfc);
}
