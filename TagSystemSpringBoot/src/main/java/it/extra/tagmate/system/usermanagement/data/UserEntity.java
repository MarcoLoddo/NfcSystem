package it.extra.tagmate.system.usermanagement.data;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;

@Entity(name="Users")
@Table (name="users")
public class UserEntity {
	@Id
    @GeneratedValue
    private Integer user_id;
      
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
        return "User [id=" + user_id + ", firstName=" + name
                + ", lastName=" + surname + ", email=" + email
                +", nfc="+nfc+"]\n\n";
    }
}