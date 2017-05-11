package it.extrasys.tesi.tagsystem.meal_service.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;

/**
 * The Interface MealDao.
 */
public interface MealDao extends JpaRepository<MealEntity, Integer> {

    /**
     * Find by price.
     *
     * @param price
     *            the price
     * @return the list
     */
    @Query("select m from Meals m where m.price=?1")
    List<MealEntity> findByPrice(int price);
}
