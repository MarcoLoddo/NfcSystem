package it.extrasys.tesi.tagsystem.corner_service.db.manager;

import java.util.List;

import javax.transaction.Transactional;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

public interface NfcReaderManager {

    NfcReaderEntity getById(String id);

    List<NfcReaderEntity> getAll();

    NfcReaderEntity add(NfcReaderEntity reader);

    NfcReaderEntity update(NfcReaderEntity reader);

}