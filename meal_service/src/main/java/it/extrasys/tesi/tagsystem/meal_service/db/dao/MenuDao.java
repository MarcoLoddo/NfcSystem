package it.extrasys.tesi.tagsystem.meal_service.db.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

/**
 * The Interface MenuDao.
 */
public interface MenuDao extends JpaRepository<MenuEntity, Serializable> {

    /**
     * Find by id.
     *
     * @param id
     *            the id
     * @return the menu entity
     */
    @Query("select m from Menus m where m.menuId=?1")
    MenuEntity findById(int id);
}
