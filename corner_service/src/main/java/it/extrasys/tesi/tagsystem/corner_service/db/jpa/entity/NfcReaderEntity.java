package it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class NfcReaderEntity {

    @Id
    @Column(name = "reader_id")
    private String readerId;

    public String getReaderId() {
        return this.readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }
}
