package it.extrasys.tesi.tagsystem.user_service.resources;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.user_service.api.LoginDto;
import it.extrasys.tesi.tagsystem.user_service.api.UserDto;
import it.extrasys.tesi.tagsystem.user_service.db.UserManager;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

/**
 * @author marco
 * @version 1.0.0 convenzione rest mapping /collezione/risorsa/azione
 */
@RestController
@RequestMapping("/user")
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
	@RequestMapping(path = "/session")
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
	@RequestMapping(path = "/update")
	public UserDto update(@RequestBody UserDto user) {
		UserEntity userEntity = new UserEntity(user);
		manager.updateUser(userEntity);
		UserEntity updated = manager.findById(userEntity.getUser_id());
		return updated.convertToDto();
	}

	@RequestMapping(path = "/{name}/find", method = RequestMethod.GET)
	public List<UserDto> findUserByName(@PathVariable String name) {
		List<UserEntity> entities = manager.findByName(name);
		List<UserDto> dtos = new ArrayList<>();
		for (UserEntity entity : entities) {
			dtos.add(entity.convertToDto());
		}
		return dtos;
	}

}
