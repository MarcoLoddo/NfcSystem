package it.extra.tagmate.system.usermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;

public interface NfcDao extends CrudRepository<NfcTagEntity, Long>{
	@Query("select n from NfcTags n where nfc_id = ?1")
	NfcTagEntity getByTag(String tag);
	@Query("select n from NfcTags n where n.user= ?1")
	List<NfcTagEntity> findByUser(UserEntity user);
}
