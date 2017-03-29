package it.extra.tagmate.system.usermanagement.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.extra.tagmate.system.usermanagement.controller.serializing.NfcTagSerializer;
import it.extra.tagmate.system.usermanagement.data.UserEntity;

@Entity(name="NfcTags")
@Table (name="nfctags")
@JsonSerialize(using=NfcTagSerializer.class)
public class NfcTagEntity {
	@Id
	private String nfc_id;
	@ManyToOne
	@JoinColumn(name = "user_Id")
	private UserEntity user;
	
	private boolean disabled;
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public UserEntity getUser() {
		return user;
	}
	public  void setUser(UserEntity user) {
		this.user=user;
	}
	public String getNfcId() {
		return nfc_id;
	}
	public void setNfcId(String value) {
		nfc_id=value;
	}
	@Override
	public String toString()
	{
		return nfc_id +", disabled=" +disabled;
	}
}
