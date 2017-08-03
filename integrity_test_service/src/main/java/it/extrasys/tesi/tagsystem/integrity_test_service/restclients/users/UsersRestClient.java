package it.extrasys.tesi.tagsystem.integrity_test_service.restclients.users;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.NfcTagDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.UserDto;

public interface UsersRestClient {

    UserDto addUser(UserDto userDto);
    UserDto addNfc(UserDto userDto, NfcTagDto nfcTagDto);
    UserDto getById(Long id);
    UserDto updateNfc(Long id, NfcTagDto oldTag, NfcTagDto newTag);

}