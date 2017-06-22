package it.extrasys.tesi.tagsystem.corner_service.api;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;

public class CornerDto {
    private Long cornerId;

    private Long mealId;

    private NfcReaderEntity reader;

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

    public NfcReaderEntity getReader() {
        return this.reader;
    }

    public void setReader(NfcReaderEntity reader) {
        this.reader = reader;
    }

}
