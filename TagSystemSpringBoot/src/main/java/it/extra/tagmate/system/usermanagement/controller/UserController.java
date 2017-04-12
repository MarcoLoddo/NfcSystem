package it.extra.tagmate.system.usermanagement.controller;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.only;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.research.ws.wadl.Method;

import it.extra.tagmate.system.usermanagement.controller.dto.LoginDto;
import it.extra.tagmate.system.usermanagement.controller.dto.UserDto;
import it.extra.tagmate.system.usermanagement.data.UserEntity;
import it.extra.tagmate.system.usermanagement.manager.UserManager;

/**
 * @author marco
 *
 */
@RestController
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserManager manager;

	/**
	 * @param email
	 *            email of the user
	 * @param password
	 *            password used to login
	 * @return null if user is not in the database, return a UserDTO(in JSON) if
	 *         found
	 */
	@RequestMapping(value="/user/session")
	public UserDto login(@RequestBody LoginDto loginData) {
		try {
			return manager.findUser(new UserEntity(loginData)).convertToDto();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param user
	 *            New User data to be updated
	 * @return User User(DTO) updated
	 */
	@RequestMapping("/user/update")
	public UserDto update(@RequestBody UserDto user) {
		UserEntity userEntity = new UserEntity(user);
		manager.updateUser(userEntity);
		UserEntity updated = manager.findById(userEntity.getUser_id());
		return updated.convertToDto();
	}
	
	
	@RequestMapping("/user/find/name")
	public List<UserDto> findUserByName(@RequestBody String nameToFind)
	{
		List<UserEntity> entities=manager.findByName(nameToFind);
		List<UserDto> dtos=new ArrayList<>();
		for (UserEntity entity : entities) {
			dtos.add(entity.convertToDto());
		}
		return dtos;
	}

}
