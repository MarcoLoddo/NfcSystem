package it.extrasys.tesi.tagsystem.corner_service.api;

public class CornerDto {
    private Long cornerId;

    private Long mealId;

    private NfcReaderDto reader;

    public Long getCornerId() {
        return this.cornerId;
    }

    public void setCornerId(Long cornerId) {
        this.cornerId = cornerId;
    }

    public Long getMealId() {
        return this.mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public NfcReaderDto getReader() {
        return this.reader;
    }

    public void setReader(NfcReaderDto reader) {
        this.reader = reader;
    }

}
