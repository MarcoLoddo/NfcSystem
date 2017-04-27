package it.extrasys.tesi.tagsystem.user_web.events;

import it.extrasys.tesi.tagsystem.user_web.client.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_web.client.UserDto;

/**
 * Event class for storing event data when an information is edited.
 *
 * @author marco
 *
 */
public class EditEvent {

    private EditEventInterface caller;
    private UserDto userDto;
    private NfcTagDto nfcTagDto;
    /**
     * Constructor user edit.
     *
     * @param caller
     * @param data
     */
    public EditEvent(EditEventInterface caller, UserDto user) {
        this.caller = caller;
        this.userDto = user;

    }
    /**
     * Constructor nfcedit.
     *
     * @param caller
     * @param data
     */
    public EditEvent(EditEventInterface caller, UserDto user, NfcTagDto nfc) {
        this.caller = caller;
        this.nfcTagDto = nfc;
        this.userDto = user;
    }

    public EditEventInterface getCaller() {
        return this.caller;
    }
    public NfcTagDto getNfcTagDto() {
        return this.nfcTagDto;
    }
    public UserDto getUserDto() {
        return this.userDto;
    }

    public void setCaller(EditEventInterface caller) {
        this.caller = caller;
    }
    public void setNfcTagDto(NfcTagDto nfcTagDto) {
        this.nfcTagDto = nfcTagDto;
    }
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

}
