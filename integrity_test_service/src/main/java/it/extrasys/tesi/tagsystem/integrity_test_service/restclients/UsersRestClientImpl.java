package it.extrasys.tesi.tagsystem.integrity_test_service.restclients;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.integrity_test_service.Messages;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.NfcTagDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.NfcUpdateDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.UserDto;

@Component
public class UsersRestClientImpl implements UsersRestClient {
    /** The rest template. */
    private RestTemplate restTemplate;

    @Autowired
    private Messages messages;
    /**
     * Instantiates a new rest client.
     */
    public UsersRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * UsersRestClient#addUser(it.extrasys.tesi.tagsystem.integrity_test_service
     * .api.users.UserDto)
     */
    @Override
    public UserDto addUser(UserDto userDto) {
        String uri = this.messages.getMessages("add.user");
        UserDto userPersisted = this.restTemplate
                .postForEntity(uri, userDto, UserDto.class).getBody();
        return userPersisted;
    }

    @Override
    public UserDto addNfc(UserDto userDto, NfcTagDto nfcTagDto) {
        String uri = this.messages.getMessages("add.nfc");
        Map<String, Long> map = new HashMap<>();
        map.put("id", userDto.getUserId());
        UserDto userPersisted = this.restTemplate
                .postForEntity(uri, nfcTagDto, UserDto.class, map).getBody();
        return userPersisted;
    }
    @Override
    public UserDto getById(Long id) {
        // TODO Auto-generated method stub
        String uri = this.messages.getMessages("get.user.by.id");
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        UserDto userPersisted = this.restTemplate
                .getForEntity(uri, UserDto.class, map).getBody();
        return userPersisted;
    }
    @Override
    public UserDto updateNfc(Long id, NfcTagDto oldTag, NfcTagDto newTag) {
        NfcUpdateDto updateDto = new NfcUpdateDto();
        updateDto.setOlNfcTagDto(oldTag);
        updateDto.setNewNfcTagDto(newTag);
        String uri = this.messages.getMessages("get.user.by.id");
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        this.restTemplate.put(uri, updateDto, map);
        return getById(id);
    }
}
