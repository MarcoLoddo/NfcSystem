package data;

import java.util.List;

public class UserDto {
	private int user_id;
	private String name;
	private String email;
	private String password;
	private List<NfcTagDto> nfcTags;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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
