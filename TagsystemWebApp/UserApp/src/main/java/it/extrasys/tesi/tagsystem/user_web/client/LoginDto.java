package it.extrasys.tesi.tagsystem.user_web.client;

/**
 * Login information class container. It's used to pass serialized
 * information(in JSON) to other app/services.
 *
 * @author marco
 *
 */
public class LoginDto extends Dto {

    private String email;
    private String password;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
