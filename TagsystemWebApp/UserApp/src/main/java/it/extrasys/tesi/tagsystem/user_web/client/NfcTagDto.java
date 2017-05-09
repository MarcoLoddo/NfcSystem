package it.extrasys.tesi.tagsystem.user_web.client;

/**
 * Nfctag information class container. It's used to pass serialized
 * information(in JSON) to other app/services
 *
 * @author marco
 *
 */
public class NfcTagDto extends Dto {

    private boolean disabled;
    private String nfcId;

    public String getNfcId() {
        return this.nfcId;
    }
    public boolean isDisabled() {
        return this.disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

}
