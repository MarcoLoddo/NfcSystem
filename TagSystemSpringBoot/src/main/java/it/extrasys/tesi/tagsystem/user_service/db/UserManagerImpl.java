package it.extrasys.tesi.tagsystem.user_service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.NfcDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.UserDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

/**
 * The implementation Class of the User Manager.
 */
@Component
public class UserManagerImpl implements UserManager {

    /** The user dao. */
    @Autowired
    private UserDao userDao;

    /** The nfc dao. */
    @Autowired
    private NfcDao nfcDao;

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.user_service.db.UserManager#findById(int)
     */
    @Override
    @Transactional
    public UserEntity findById(int id) {
        UserEntity userEntity = this.userDao.findById(id);
        return userEntity;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.user_service.db.UserManager#findByName(java.
     * lang.String)
     */
    @Override
    @Transactional
    public List<UserEntity> findByName(String name) {
        if (name.toLowerCase().equals("all")) {
            return this.userDao.findAll();
        }
        return this.userDao.findUserByName(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.user_service.db.UserManager#findNfcOfUser(it.
     * extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity)
     */
    @Override
    @Transactional
    public List<NfcTagEntity> findNfcOfUser(UserEntity user) {
        return this.nfcDao.findByUser(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.user_service.db.UserManager#findUser(it.
     * extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity)
     */
    @Override
    @Transactional
    public UserEntity findUser(UserEntity user) {
        return this.userDao.findByEmailPassword(user.getEmail(),
                user.getPassword());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.user_service.db.UserManager#updateUser(it.
     * extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity)
     */
    @Override
    @Transactional
    public void updateUser(UserEntity user) {
        if (user.getNfcTags() != null) {
            for (NfcTagEntity nfc : user.getNfcTags()) {
                nfc.setUser(user);
                this.nfcDao.save(nfc);
            }

        } else {
            // declaration in case of null collection to prevent hibernate from
            // not fetching the list
            // if the collection is not declared, hibernate can't put in the
            // data because
            // list is an abstract type which hasn't a unique implementation
            user.setNfcTags(new ArrayList<NfcTagEntity>());
        }

        // controlli per l'integrità dei dati dell'user
        this.userDao.save(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.user_service.db.UserManager#userListByName(
     * java.lang.String)
     */
    @Override
    @Transactional
    public List<UserEntity> userListByName(String name) {
        return this.userDao.findUserByName(name);
    }

}
