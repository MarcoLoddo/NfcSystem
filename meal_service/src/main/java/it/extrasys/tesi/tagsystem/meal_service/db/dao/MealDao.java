package it.extrasys.tesi.tagsystem.meal_service.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;

/**
 * The Interface MealDao.
 */
public interface MealDao extends JpaRepository<MealEntity, Long> {

    /**
     * Find by price.
     *
     * @param price
     *            the price
     * @return the list
     */
    List<MealEntity> findByPrice(BigDecimal price);
}
