package it.extrasys.tesi.tagsystem.user_service.db.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

public interface NfcDao extends JpaRepository<NfcTagEntity, Long>{
	@Query("select n from NfcTags n where nfc_id = ?1")
	NfcTagEntity getByTag(String tag);
	@Query("select n from NfcTags n where n.user= ?1")
	List<NfcTagEntity> findByUser(UserEntity user);
}
