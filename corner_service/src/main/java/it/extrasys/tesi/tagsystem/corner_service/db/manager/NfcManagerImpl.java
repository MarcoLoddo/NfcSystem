package it.extrasys.tesi.tagsystem.corner_service.db.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.dao.NfcReaderDao;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

@Component
public class NfcManagerImpl implements NfcReaderManager {
    @Autowired
    private NfcReaderDao readerDao;

    @Transactional
    public NfcReaderEntity getById(String id) {
        return this.readerDao.findOne(id);
    }
    @Transactional
    public List<NfcReaderEntity> getAll() {
        return this.readerDao.findAll();
    }
    @Transactional
    public NfcReaderEntity add(NfcReaderEntity reader) {
        return this.readerDao.save(reader);
    }
    @Transactional
    public NfcReaderEntity update(NfcReaderEntity reader) {
        return this.readerDao.save(reader);
    }
}
