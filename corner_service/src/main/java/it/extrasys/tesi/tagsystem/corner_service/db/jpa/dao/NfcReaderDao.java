package it.extrasys.tesi.tagsystem.corner_service.db.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

public interface NfcReaderDao extends JpaRepository<NfcReaderEntity, String> {

}
