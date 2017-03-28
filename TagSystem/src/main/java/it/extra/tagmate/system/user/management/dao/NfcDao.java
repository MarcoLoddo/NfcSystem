package it.extra.tagmate.system.user.management.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.extra.tagmate.system.user.management.entity.NfcTagEntity;
import it.extra.tagmate.system.user.management.entity.UserEntity;

public interface NfcDao extends CrudRepository<NfcTagEntity, Long> {


}
