package it.extra.tagmate.system.user.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.extra.tagmate.system.user.management.entity.UserEntity;
@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {
	@Query("SELECT u FROM Users WHERE name = :name")
	List<UserEntity> findByName(String name);
	@Query("SELECT u from Users JOIN FETCH u.nfc n where email = :email and password = :password")
	UserEntity findByEmailPassword(String email, String password);
}
