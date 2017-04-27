package it.extrasys.tesi.tagsystem.user_service.db.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Nfc tag entity class.
 *
 * @author marco
 *
 */
@Entity(name = "NfcTags")
@Table(name = "nfctags")
public class NfcTagEntity {
    @Id
    @Column(name = "nfc_id", unique = true)
    private String nfcId;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserEntity user;

    private boolean disabled;
    public String getNfcId() {
        return this.nfcId;
    }
    public UserEntity getUser() {
        return this.user;
    }
    public boolean isDisabled() {
        return this.disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public void setNfcId(String value) {
        this.nfcId = value;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return this.nfcId + ", disabled=" + this.disabled;
    }
}
