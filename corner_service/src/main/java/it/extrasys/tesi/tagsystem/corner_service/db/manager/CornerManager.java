package it.extrasys.tesi.tagsystem.corner_service.db.manager;

import java.util.List;

import javax.transaction.Transactional;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.CornerEntity;

public interface CornerManager {

    CornerEntity add(CornerEntity corner);

    CornerEntity updateMealId(Long cornerId, Long mealId);

    CornerEntity updateReader(Long cornerId, String readerId);

    CornerEntity update(CornerEntity corner);

    List<CornerEntity> getAll();

    CornerEntity getById(Long id);

}