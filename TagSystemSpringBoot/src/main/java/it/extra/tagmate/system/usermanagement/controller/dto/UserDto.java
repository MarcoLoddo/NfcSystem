package it.extra.tagmate.system.usermanagement.controller.dto;

import java.util.List;

public class UserDto {
	private int user_id;
	private String email;
	private String password;
	private List<NfcTagDto> nfcTags;

	public List<NfcTagDto> getNfcTags() {
		return nfcTags;
	}

	public void setNfcTags(List<NfcTagDto> nfcTags) {
		this.nfcTags = nfcTags;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
