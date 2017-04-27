package it.extrasys.tesi.tagsystem.user_service.resources;

import java.util.ArrayList;
import java.util.List;

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

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /** The manager. */
    @Autowired
    private UserManager manager;

    /**
     * Find user by name service.
     *
     * @param name
     *            the name
     * @return the list
     */
    @RequestMapping(path = "/{name}/find", method = RequestMethod.GET)
    public List<UserDto> findUserByName(@PathVariable String name) {
        List<UserEntity> entities = this.manager.findByName(name);
        List<UserDto> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(entity.convertToDto());
        }
        return dtos;
    }

    /**
     * Login service.
     *
     * @param loginData
     *            the login data
     * @return the user dto
     */
    @RequestMapping(path = "/session")
    public UserDto login(@RequestBody LoginDto loginData) {
        try {
            return this.manager.findUser(new UserEntity(loginData))
                    .convertToDto();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update user service.
     *
     * @param user
     *            the user
     * @return the user dto
     */
    @RequestMapping(path = "/update")
    public UserDto update(@RequestBody UserDto user) {
        UserEntity userEntity = new UserEntity(user);
        this.manager.updateUser(userEntity);
        UserEntity updated = this.manager.findById(userEntity.getUserId());
        UserDto updatedDto = updated.convertToDto();
        return updatedDto;
    }
}
