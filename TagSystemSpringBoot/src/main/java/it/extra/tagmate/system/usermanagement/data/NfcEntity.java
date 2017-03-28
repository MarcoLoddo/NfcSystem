package it.extra.tagmate.system.usermanagement.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.extra.tagmate.system.usermanagement.data.UserEntity;

@Entity(name="NfcTags")
@Table (name="nfctags")
public class NfcEntity {
	@Id
	private String nfcId;
	@ManyToOne
	@JoinColumn(name = "userId")
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
		return nfcId;
	}
	public void setNfcId(String value) {
		nfcId=value;
	}
	@Override
	public String toString()
	{
		return nfcId +", disabled=" +disabled;
	}
}
