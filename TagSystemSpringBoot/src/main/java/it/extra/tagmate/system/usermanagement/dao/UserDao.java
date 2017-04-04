package it.extra.tagmate.system.usermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;
public interface UserDao extends JpaRepository<UserEntity, Long> {
	@Query("SELECT u FROM Users u WHERE u.name LIKE ?1")
	List<UserEntity> findUserByName(String fname);
	@Query("SELECT u FROM Users u JOIN FETCH u.nfcTags n WHERE u.user_id = ?1")
	UserEntity findById(int id);
	@Query("SELECT u FROM Users u JOIN FETCH u.nfcTags n WHERE u.email = ?1 AND u.password = ?2")
	UserEntity findByEmailPassword(String mail, String pwd);
	@Query("select n.user from NfcTags n where nfc_id = ?1")
	UserEntity getUserByNfc(NfcTagEntity nfc);
}
