package it.extra.tagmate.system.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	 * @return null if user is not in the database, return a User if found
	 */
	@RequestMapping("/login")
	public String login(@RequestParam(value="email") String email,@RequestParam(value="password")String password) {
		UserEntity logging= new UserEntity();
		logging.setEmail(email);
		logging.setPassword(password);
		//return JsonSerializer.serialize(manager.login(logging));
		try {
			return new ObjectMapper().writeValueAsString(manager.login(logging));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
