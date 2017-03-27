package it.extra.tagmate.system.user.management.dao;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import it.extra.tagmate.system.user.management.entity.NfcTagEntity;
import it.extra.tagmate.system.user.management.entity.UserEntity;

@Component
public class UserDaoImpl implements UserDao {
	@PersistenceContext
    private EntityManager manager;
	public void flush()
	{
		manager.flush();
	}
	public List<UserEntity> getUsersByName(String name) {
		@SuppressWarnings("unchecked")
		List<UserEntity> list=manager.createQuery("SELECT c FROM Users c WHERE c.name LIKE :uname").setParameter("uname", name).getResultList();
		return list;
	}
	
	public List<UserEntity> getAll() {
		@SuppressWarnings("unchecked")
		List<UserEntity> list=manager.createQuery("SELECT c FROM Users c").getResultList();
		return list;
	}
	public void insertNew(UserEntity userEntity) {
		try{
            manager.persist(userEntity);
        }catch(Exception e){
        	e.printStackTrace();
        }	
	}
	public UserEntity getUserByNfc(NfcTagEntity nfc)
	{
		return nfc.getUser();
	}
	public UserEntity getByUserPassword(String email, String password)
	{
		try {
			UserEntity userEntity=(UserEntity)manager.createQuery("SELECT u FROM Users u JOIN FETCH u.nfc WHERE u.email = :email AND u.password = :password")
					.setParameter("email", email).setParameter("password", password).getSingleResult();
			return userEntity;
		} catch (Exception e) {
			
			return null;
		}
		
	}

	@Override
	public void update(UserEntity userEntity) {
		manager.merge(userEntity);
		
	}
	public void update(List<UserEntity> userEntities) {
		for (UserEntity userEntity : userEntities) {
			manager.merge(userEntity);
		}
		
	}
}
