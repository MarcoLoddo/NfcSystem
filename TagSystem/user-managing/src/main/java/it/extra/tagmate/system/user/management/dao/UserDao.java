package it.extra.tagmate.system.user.management.dao;

import java.util.List;
import it.extra.tagmate.system.user.management.entity.NfcTagEntity;


import it.extra.tagmate.system.user.management.entity.UserEntity;

public interface UserDao {
	public List<UserEntity> getUsersByName(String name) ;
	public List<UserEntity> getAll() ;
	public void insertNew(UserEntity u);
	public UserEntity getUserByNfc(NfcTagEntity nfc);
	public UserEntity getByUserPassword(String email, String password);
	public void update(UserEntity userEntity);
	public void flush();
}
