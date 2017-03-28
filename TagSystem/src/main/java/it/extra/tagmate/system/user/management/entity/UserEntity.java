package it.extra.tagmate.system.user.management.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
  
@Entity(name="Users")
@Table (name="Users")
public class UserEntity {
	@Id
    @GeneratedValue
    private Integer userId;
      
    private String name;
    private String surname;
    private String email;
    private String password;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private List<NfcTagEntity> nfc;
    
	public void setNfc(List<NfcTagEntity> nfc) {
		this.nfc = nfc;
	}
	public List<NfcTagEntity> getNfc() {
		return nfc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserId() {
		return userId;
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
        return "User [id=" + userId + ", firstName=" + name
                + ", lastName=" + surname + ", email=" + email
                +", nfc="+nfc+"]\n\n";
    }
}
