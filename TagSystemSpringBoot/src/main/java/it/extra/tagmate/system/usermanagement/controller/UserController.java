package it.extra.tagmate.system.usermanagement.controller;

import java.util.List;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.extra.tagmate.system.usermanagement.controller.dto.NfcTagDto;
import it.extra.tagmate.system.usermanagement.controller.dto.UserDto;
import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;
import it.extra.tagmate.system.usermanagement.manager.UserManager;

/**
 * @author marco
 *
 */
@RestController
public class UserController {
	@Autowired private UserManager manager;
	
	/**
	 * @param email email of the user
	 * @param password passwrod used to login
	 * @return null if user is not in the database, return a UserDTO(in JSON) if found
	 */
	@RequestMapping("/login")
	public UserDto login(@RequestBody UserDto logging) {
		UserEntity userEntity=new UserEntity();
		userEntity.setEmail(logging.getEmail());
		userEntity.setPassword(logging.getPassword());
		UserEntity logged=manager.login(userEntity);
		List<NfcTagDto> nfcs=new ArrayList<>();
		for (NfcTagEntity nfc : logged.getNfc()) {
			NfcTagDto newDto=new NfcTagDto();
			newDto.setNfc_id(nfc.getNfcId());
			newDto.setDisabled(nfc.isDisabled());
			nfcs.add(newDto);
		}
		logging.setNfcTags(nfcs);
		return logging;
	}
	
}
