package it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Corners")
@Table(name = "corners")
public class CornerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corner_id")
    private Long cornerId;

    private Long mealId;

    @OneToOne
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
