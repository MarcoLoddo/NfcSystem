package it.extrasys.tesi.tagsystem.user_web.client;

/**
 * The Class NfcUpdateDto.
 */
public class NfcUpdateDto extends Dto {

    private NfcTagDto olNfcTagDto, newNfcTagDto;

    public NfcTagDto getNewNfcTagDto() {
        return this.newNfcTagDto;
    }

    public NfcTagDto getOlNfcTagDto() {
        return this.olNfcTagDto;
    }

    public void setNewNfcTagDto(NfcTagDto newNfcTagDto) {
        this.newNfcTagDto = newNfcTagDto;
    }

    public void setOlNfcTagDto(NfcTagDto olNfcTagDto) {
        this.olNfcTagDto = olNfcTagDto;
    }

}
