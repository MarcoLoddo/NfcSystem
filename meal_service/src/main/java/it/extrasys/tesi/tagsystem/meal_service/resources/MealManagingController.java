package it.extrasys.tesi.tagsystem.meal_service.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.meal_service.api.DtoConverter;
import it.extrasys.tesi.tagsystem.meal_service.api.MealDto;
import it.extrasys.tesi.tagsystem.meal_service.api.MenuDto;
import it.extrasys.tesi.tagsystem.meal_service.db.MealManaging;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

/**
 * The Class MealManagingController.
 */
@RestController
public class MealManagingController {

    @Autowired
    private MealManaging manager;

    /**
     * Adds the meal.
     *
     * @param meal
     *            the meal
     */
    @RequestMapping("/meal/add")
    public void addMeal(@RequestBody MealDto meal) {
        this.manager.addMeal(DtoConverter.toEntity(meal));
    }

    /**
     * Adds the mealto menu.
     *
     * @param menuId
     *            the menu id
     * @param mealId
     *            the meal id
     */
    @RequestMapping("/menu/{menuId}/meal/{mealId}/add")
    public void addMealtoMenu(@PathVariable int menuId,
            @PathVariable int mealId) {
        MealEntity mealEntity = this.manager.getMeal(mealId);
        mealEntity.addToMenu(this.manager.getMenu(menuId));
        this.manager.addMeal(mealEntity);
    }
    /**
     * Adds the menu.
     */
    @RequestMapping("/menu/add")
    public void addMenu(@RequestBody MenuDto menu) {
        MenuEntity menuEntity = DtoConverter.menuDtotoEntity(menu);
        this.manager.addMenu(menuEntity);

    }

    /**
     * Gets the menus of day.
     *
     * @param date
     *            the date
     * @return the menus of day
     */
    @RequestMapping("/menu/{date}/find")
    public List<MenuDto> getMenusOfDay(@PathVariable String date) {

        return DtoConverter
                .menuEntitytoDtoList(this.manager.getMenuByDate(date));
    }

}
