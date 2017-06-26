package it.extrasys.tesi.tagsystem.corner_service.api;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

public final class NfcReaderDtoConverter {

    private NfcReaderDtoConverter() {

    }
    public static NfcReaderDto toDto(NfcReaderEntity reader) {
        NfcReaderDto dto = new NfcReaderDto();
        dto.setReaderId(reader.getReaderId());
        return dto;
    }
    public static NfcReaderEntity toEntity(NfcReaderDto reader) {
        NfcReaderEntity nfcReaderEntity = new NfcReaderEntity();
        nfcReaderEntity.setReaderId(reader.getReaderId());
        return nfcReaderEntity;
    }
}
