package it.extra.tagmate.system.usermanagement.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.extra.tagmate.system.usermanagement.data.UserEntity;

@Entity(name="NfcTags")
@Table (name="nfctags")
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
	public String getNfc_id() {
		return nfc_id;
	}
	public void setNfc_id(String value) {
		nfc_id=value;
	}
	@Override
	public String toString()
	{
		return nfc_id +", disabled=" +disabled;
	}
}
