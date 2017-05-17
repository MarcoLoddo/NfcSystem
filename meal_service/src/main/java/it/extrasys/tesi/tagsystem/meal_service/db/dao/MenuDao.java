package it.extrasys.tesi.tagsystem.meal_service.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

/**
 * The Interface MenuDao.
 */
public interface MenuDao extends JpaRepository<MenuEntity, Long> {

    /**
     * Find by date.
     *
     * @param date
     *            the date
     * @return the list
     */
    @Query("SELECT DISTINCT m FROM Menus m  LEFT JOIN FETCH m.meals WHERE m.date=?1")
    List<MenuEntity> findByDate(Date date);

}
