package it.extra.tagmate.system.user.management.dao;

import java.util.List;

import it.extra.tagmate.system.user.management.entity.NfcTagEntity;
import it.extra.tagmate.system.user.management.entity.UserEntity;

public interface NfcDao {
	public List<NfcTagEntity> getByUser(UserEntity user) ;
	public List<NfcTagEntity> getByStatus(boolean status);
	public  void insertNew(NfcTagEntity nfc);
	public NfcTagEntity getByTag(String tag);
	public void update(NfcTagEntity nfcTagEntity) ;
	public void update(List<NfcTagEntity> nfcTagEntities);

}
