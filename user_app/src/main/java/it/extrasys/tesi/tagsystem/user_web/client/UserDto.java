package it.extrasys.tesi.tagsystem.user_web.client;

import java.util.List;
/**
 * User information class container. It's used to pass serialized information(in
 * JSON) to other app/services
 *
 * @author marco
 *
 */
public class UserDto extends Dto {
    private int userId;
    private String name;

    private String email;

    private String password;
    private List<NfcTagDto> nfcTags;

    @Override
    public boolean equals(Object obj) {
        UserDto objDto = (UserDto) obj;
        return getUserId() == objDto.getUserId();
    }
    public String getEmail() {
        return this.email;
    }
    public String getName() {
        return this.name;
    }
    public List<NfcTagDto> getNfcTags() {
        return this.nfcTags;
    }
    public String getPassword() {
        return this.password;
    }

    public int getUserId() {
        return this.userId;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNfcTags(List<NfcTagDto> nfcTags) {
        this.nfcTags = nfcTags;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Size nfc tags.
     *
     * @return the integer
     */
    public Integer sizeNfcTags() {
        return this.nfcTags.size();
    }

}
