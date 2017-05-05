package it.extrasys.tesi.tagsystem.user_service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.NfcDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.UserDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

/**
 * The Class Test.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class SpringTest {

    @Autowired
    private EntityManager manager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NfcDao nfcDao;

    /**
     * Adds the nfc.
     */

    @Test
    @Commit
    public void addNfc() {
        addUser();
        UserEntity userEntity = this.userDao.findByEmailPassword("super@man",
                "clarkent");
        NfcTagEntity nfcTagEntity = new NfcTagEntity();
        nfcTagEntity.setNfcId("prova1");
        nfcTagEntity.setUser(userEntity);
        userEntity.addNewNfc(nfcTagEntity);
        this.nfcDao.save(nfcTagEntity);
        this.userDao.save(userEntity);

    }

    private void addUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("super@man");
        userEntity.setFirstName("Clark");
        userEntity.setLastName("Kent");
        userEntity.setPassword("clarkent");
        NfcTagEntity nfcTagEntity = new NfcTagEntity();
        nfcTagEntity.setNfcId("provanfc");
        nfcTagEntity.setUser(userEntity);
        List<NfcTagEntity> list = new ArrayList<>();
        list.add(nfcTagEntity);

        userEntity.setNfcTags(list);

        this.userDao.save(userEntity);

        this.nfcDao.save(nfcTagEntity);
    }
    /**
     * Adds the user.
     */
    @Test
    @Commit
    public void addUserTest() {
        addUser();
    }
    /**
     * Read nfc.
     */
    @Test
    @Commit
    public void readNfc() {
        addUser();
        UserEntity userEntity = this.userDao.findByEmailPassword("super@man",
                "clarkent");
        System.out.println(this.nfcDao.findByUser(userEntity));
    }

    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setup() throws Exception {
        this.nfcDao.deleteAll();
        this.userDao.deleteAll();

    }
    /**
     * Show.
     */
    @AfterTransaction
    public void show() {

        System.out.println("\n\n\n\n\n");
        System.out.println(this.userDao.findAll());

        System.out.println("\n\n\n\n\n");
        System.out.println(this.nfcDao.findAll());
        System.out.println("\n\n\n\n\n");

    }
    /**
     * Update nfc.
     */
    @Test
    @Commit
    public void updateNfc() {
        addUser();
        NfcTagEntity oldNfc = this.nfcDao.findNfcById("provanfc");
        NfcTagEntity newNfc = new NfcTagEntity();

        newNfc.setNfcId("provanfc2");
        newNfc.setUser(oldNfc.getUser());
        this.nfcDao.delete(oldNfc);
        this.nfcDao.save(newNfc);
        assertTrue(this.nfcDao.findNfcById("provanfc2") != null);
    }

}
