package it.extrasys.tesi.tagsystem.corner_service.db.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.CornerEntity;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

public interface CornerDao extends JpaRepository<CornerEntity, Long> {

    CornerEntity findByMealId(Long mealId);

    CornerEntity findByReader(NfcReaderEntity reader);
}
