package it.extra.tagmate.system.usermanagement.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Autowired private UserManager manager;
	
	/**
	 * @param email
	 *            email of the user
	 * @param password
	 *            password used to login
	 * @return null if user is not in the database, return a UserDTO(in JSON) if
	 *         found
	 */
	@RequestMapping("/login")
	public UserDto login(@RequestBody UserDto loginData) {
		return manager.findUser(new UserEntity(loginData)).convertToDto();
	}
	
	/** 
	 * @param user New User data to be updated (parameter nfc must be present, even if empty)
	 * @return User updated
	 */
	@RequestMapping("/updateUser")
	public UserDto update(@RequestBody UserDto user) {
		UserEntity userEntity=new UserEntity(user);
		manager.updateUser(userEntity);
		UserEntity updated=manager.findById(userEntity.getUser_id());
		return updated.convertToDto();
	}

}
