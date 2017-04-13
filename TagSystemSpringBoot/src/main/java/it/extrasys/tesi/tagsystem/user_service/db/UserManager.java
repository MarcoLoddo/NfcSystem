package it.extrasys.tesi.tagsystem.user_service.db;

import java.util.List;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

public interface UserManager {
	UserEntity findUser(UserEntity user);
	List<UserEntity> findByName(String name);
	UserEntity findById(int id);
	List<UserEntity> userListByName(String name);
	void updateUser(UserEntity user);
	List<NfcTagEntity> findNfcOfUser(UserEntity user);
}
