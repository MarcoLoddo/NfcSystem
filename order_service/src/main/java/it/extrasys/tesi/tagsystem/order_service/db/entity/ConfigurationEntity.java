package it.extrasys.tesi.tagsystem.order_service.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class ConfigurationEntity.
 */
@Entity(name = "Configurations")
@Table(name = "configurations")
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuration_id")
    private int configurationId;

    private List<Integer> mealPk;

}
