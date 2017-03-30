package it.extra.tagmate.system.usermanagement.controller.dto;

public class NfcTagDto {
	
	private boolean disabled;
	private String nfc_id;
	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getNfc_id() {
		return nfc_id;
	}
	public void setNfc_id(String nfc_id) {
		this.nfc_id = nfc_id;
	}
}
