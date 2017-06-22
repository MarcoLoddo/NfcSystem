package it.extrasys.tesi.tagsystem.corner_service.db.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.dao.CornerDao;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.dao.NfcReaderDao;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.CornerEntity;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

@Component
public class CornerManagerImpl implements CornerManager {

    @Autowired
    private CornerDao cornerDao;

    @Autowired
    private NfcReaderDao readerDao;

    @Transactional
    public CornerEntity add(CornerEntity corner) {
        return this.cornerDao.save(corner);
    }

    @Transactional
    public CornerEntity updateMealId(Long cornerId, Long mealId) {
        CornerEntity cornerEntity = this.cornerDao.findOne(cornerId);

        CornerEntity cornerMeal = this.cornerDao.findByMealId(mealId);
        if (cornerMeal != null
                && cornerEntity.getCornerId() != cornerMeal.getCornerId()) {
            cornerMeal.setMealId(cornerEntity.getMealId());
            this.cornerDao.save(cornerMeal);
        }
        cornerEntity.setMealId(mealId);
        return this.cornerDao.save(cornerEntity);
    }
    @Transactional
    public CornerEntity updateReader(Long cornerId, String readerId) {
        CornerEntity cornerEntity = this.cornerDao.findOne(cornerId);
        NfcReaderEntity reader = this.readerDao.findOne(readerId);
        CornerEntity cornerReader = this.cornerDao.findByReader(reader);
        if (cornerReader != null
                && cornerEntity.getCornerId() != cornerReader.getCornerId()) {
            cornerReader.setReader(cornerEntity.getReader());
            this.cornerDao.save(cornerReader);
        }
        cornerEntity.setReader(reader);
        return this.cornerDao.save(cornerEntity);
    }
    @Transactional
    public CornerEntity update(CornerEntity corner) {
        return this.cornerDao.save(corner);
    }
    @Transactional
    public List<CornerEntity> getAll() {
        return this.cornerDao.findAll();
    }
    @Transactional
    public CornerEntity getById(Long id) {
        return this.cornerDao.findOne(id);
    }
}
