package it.extrasys.tesi.tagsystem.integrity_test_service.restclients.corners;

import java.util.List;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.CornerDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.NfcReaderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.NfcTagDto;

public interface CornerRestClient {

    CornerDto addCorner(CornerDto cornerDto);

    CornerDto getCornerById(Long id);

    CornerDto updateCorner(CornerDto cornerDto, long mealId, String readerId);
    List<CornerDto> getOrdersByStatus(Boolean status);
    NfcReaderDto addReader(NfcReaderDto nfcReaderDto);
    OrderDto callAddMealFromUser(NfcReaderDto nfcReaderDto, NfcTagDto userTag);
    NfcReaderDto getReader(String tag);
}