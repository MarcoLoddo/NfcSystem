package it.extra.tagmate.system.user.management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import it.extra.tagmate.system.user.management.entity.NfcTagEntity;
import it.extra.tagmate.system.user.management.entity.UserEntity;

@Component
public class NfcDaoImpl implements NfcDao {

	@PersistenceContext
	private EntityManager manager;
	@Override
	public List<NfcTagEntity> getByUser(UserEntity user) {
		@SuppressWarnings("unchecked")
		List<NfcTagEntity> nfcs=manager.createQuery("Select c From NfcTags c where userId = :id").setParameter("id", user.getUserId()).getResultList();
		return nfcs;
	}

	@Override
	public List<NfcTagEntity> getByStatus(boolean status) {
		@SuppressWarnings("unchecked")
		List<NfcTagEntity> nfcs=manager.createQuery("Select c From NfcTags c where disabled = :status").setParameter("status", status).getResultList();
		return nfcs;
	}
	
	@Override
	public void insertNew(NfcTagEntity nfc) {

		try {
			manager.persist(nfc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public NfcTagEntity getByTag(String tag) {
		return (NfcTagEntity)manager.createQuery("Select c From NfcTags c where nfcId = :tag").setParameter("tag", tag).getSingleResult();
	}

	@Override
	public void update(NfcTagEntity nfcTagEntity) {
		
		manager.merge(nfcTagEntity);
	}
	public void update(List<NfcTagEntity> nfcTagEntities)
	{
		for (NfcTagEntity nfcTagEntity : nfcTagEntities) {
			manager.merge(nfcTagEntity);
		}
	}
	
}
