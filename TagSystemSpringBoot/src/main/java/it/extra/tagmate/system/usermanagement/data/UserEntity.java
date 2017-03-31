package it.extra.tagmate.system.usermanagement.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.extra.tagmate.system.usermanagement.controller.dto.NfcTagDto;
import it.extra.tagmate.system.usermanagement.controller.dto.UserDto;

@Entity(name = "Users") @Table(name = "users")
public class UserEntity {
	@Id @GeneratedValue private Integer user_id;

	private String name;
	private String surname;
	private String email;
	private String password;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user") private List<NfcTagEntity> nfcTags;

	public List<NfcTagEntity> getNfcTags() {
		return nfcTags;
	}

	public void setNfcTags(List<NfcTagEntity> nfcTags) {
		this.nfcTags = nfcTags;
	}

	public UserEntity() {

	}

	public UserEntity(UserDto userDto) {
		user_id = userDto.getUser_id();
		email = userDto.getEmail();
		password = userDto.getPassword();
		if (userDto.getNfcTags() != null) {
			List<NfcTagEntity> nfcs = new ArrayList<>();
			for (NfcTagDto nfc : userDto.getNfcTags()) {
				NfcTagEntity newEntity = new NfcTagEntity();
				newEntity.setNfc_id(nfc.getNfc_id());
				newEntity.setDisabled(nfc.isDisabled());
				nfcs.add(newEntity);
			}
			nfcTags = nfcs;
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getFirstName() {
		return name;
	}

	public void setFirstName(String firstName) {
		this.name = firstName;
	}

	public String getLastName() {
		return surname;
	}

	public void setLastName(String lastName) {
		this.surname = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + user_id + ", firstName=" + name + ", lastName=" + surname + ", email=" + email + ", nfc="
				+ nfcTags + "]\n\n";
	}

	public List<NfcTagDto> convertNfcTagsToDto() {
		List<NfcTagDto> nfcs = new ArrayList<>();
		for (NfcTagEntity nfc : this.getNfcTags()) {
			NfcTagDto newDto = new NfcTagDto();
			newDto.setNfc_id(nfc.getNfc_id());
			newDto.setDisabled(nfc.isDisabled());
			nfcs.add(newDto);
		}
		return nfcs;
	}

	public UserDto convertToDto() {
		UserDto userDto = new UserDto();
		userDto.setEmail(this.getEmail());
		userDto.setPassword(this.getPassword());
		userDto.setUser_id(this.getUser_id());
		if (this.nfcTags != null)
			userDto.setNfcTags(convertNfcTagsToDto());

		return userDto;
	}
}