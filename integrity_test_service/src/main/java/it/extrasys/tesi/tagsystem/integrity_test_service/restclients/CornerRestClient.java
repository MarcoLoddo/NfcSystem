package it.extrasys.tesi.tagsystem.integrity_test_service.restclients;

import java.util.List;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.CornerDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.NfcReaderDto;

public interface CornerRestClient {

    CornerDto addCorner(CornerDto cornerDto);

    CornerDto getCornerById(Long id);

    CornerDto updateCorner(CornerDto cornerDto, long mealId, String readerId);
    List<CornerDto> getOrdersByStatus(Boolean status);
    NfcReaderDto addReader(NfcReaderDto nfcReaderDto);
}