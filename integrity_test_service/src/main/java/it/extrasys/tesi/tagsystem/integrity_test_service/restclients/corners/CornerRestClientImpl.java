package it.extrasys.tesi.tagsystem.integrity_test_service.restclients.corners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.integrity_test_service.Messages;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.CornerDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.NfcReaderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.NfcTagDto;

@Component
public class CornerRestClientImpl implements CornerRestClient {

    /** The rest template. */
    private RestTemplate restTemplate;

    @Autowired
    private Messages messages;
    /**
     * Instantiates a new rest client.
     */
    public CornerRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * CornerRestClient#addCorner(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.corners.CornerDto)
     */
    @Override
    public CornerDto addCorner(CornerDto cornerDto) {
        String uri = this.messages.getMessages("add.corner");
        CornerDto cornerPersisted = this.restTemplate
                .postForEntity(uri, cornerDto, CornerDto.class).getBody();

        return cornerPersisted;
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * CornerRestClient#getCornerById(java.lang.Long)
     */
    @Override
    public CornerDto getCornerById(Long id) {
        String uri = this.messages.getMessages("get.corner.by.id");
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return this.restTemplate.getForEntity(uri, CornerDto.class, map)
                .getBody();
    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * CornerRestClient#updateCorner(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.corners.CornerDto, long, long)
     */
    @Override
    public CornerDto updateCorner(CornerDto cornerDto, long mealId,
            String readerId) {
        String uri = this.messages.getMessages("update.corner");
        Map<String, String> map = new HashMap<>();
        map.put("mealId", String.valueOf(mealId));
        map.put("readerId", readerId);
        map.put("id", String.valueOf(cornerDto.getCornerId()));
        this.restTemplate.put(uri, cornerDto, map);

        return getCornerById(cornerDto.getCornerId());
    }
    @Override
    public NfcReaderDto addReader(NfcReaderDto nfcReaderDto) {
        String uri = this.messages.getMessages("add.reader");
        NfcReaderDto readerPersisted = this.restTemplate
                .postForEntity(uri, nfcReaderDto, NfcReaderDto.class).getBody();

        return readerPersisted;
    }

    @Override
    public List<CornerDto> getOrdersByStatus(Boolean status) {
        String uri = this.messages.getMessages("get.orders.by.status");
        Map<String, Boolean> map = new HashMap<>();
        map.put("status", status);
        return this.restTemplate
                .getForEntity(uri,
                        (Class<? extends List<CornerDto>>) ArrayList.class, map)
                .getBody();
    }
    @Override
    public OrderDto callAddMealFromUser(NfcReaderDto nfcReaderDto,
            NfcTagDto userTag) {
        String uri = this.messages.getMessages("add.meal.from.user");
        Map<String, String> map = new HashMap<>();
        map.put("readerNfc", nfcReaderDto.getReaderId());
        map.put("userNfc", userTag.getNfcId());
        return this.restTemplate.postForEntity(uri, null, OrderDto.class, map)
                .getBody();
    }
}
