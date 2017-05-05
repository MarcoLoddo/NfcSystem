package it.extrasys.tesi.tagsystem.user_service.test;

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

        UserEntity userEntity = this.userDao.findByEmailPassword("super@man",
                "clarkent");
        NfcTagEntity nfcTagEntity = new NfcTagEntity();
        nfcTagEntity.setNfcId("prova1");
        nfcTagEntity.setUser(userEntity);
        userEntity.addNewNfc(nfcTagEntity);

    }
    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setup() throws Exception {
        this.userDao.deleteAll();
        this.nfcDao.deleteAll();
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
     * Show.
     */
    @AfterTransaction
    public void show() {

        System.out.println("\n\n\n\n\n");
        System.out.println(this.userDao.findAll());

        System.out.println("\n\n\n\n\n");
        System.out.println(this.nfcDao.findAll());

    }

}
