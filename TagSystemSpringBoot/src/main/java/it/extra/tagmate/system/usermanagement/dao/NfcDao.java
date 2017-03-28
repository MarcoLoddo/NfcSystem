package it.extra.tagmate.system.usermanagement.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.extra.tagmate.system.usermanagement.data.NfcEntity;

public interface NfcDao extends CrudRepository<NfcEntity, Long>{
	@Query("select n from NfcTags n where nfc_id = ?1")
	NfcEntity getByTag(String tag);
}
