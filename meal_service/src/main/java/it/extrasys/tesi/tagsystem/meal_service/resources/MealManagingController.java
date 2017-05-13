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
        this.manager.addMeal(DtoConverter.mealDtoToEntity(meal));
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
     * @param id
     *            the id
     * @return the menus of day
     */
    @RequestMapping("/menu/{id}/find")
    public MenuDto getMenusOfDay(@PathVariable int id) {

        return DtoConverter.menuEntitytoDto(this.manager.getMenu(id));
    }

    /**
     * Gets the menus of day.
     *
     * @param date
     *            the date
     * @return the menus of day
     */
    @RequestMapping("/menu/day/{date}/find")
    public List<MenuDto> getMenusOfDay(@PathVariable String date) {

        return DtoConverter
                .menuEntitytoDtoList(this.manager.getMenuByDate(date));
    }

    /**
     * Update meal.
     *
     * @param id
     *            the id
     * @param toUpdate
     *            the to update
     * @return the meal dto
     */
    @RequestMapping("/meal/{id}/update")
    public MealDto updateMeal(@PathVariable int id,
            @RequestBody MealDto toUpdate) {
        MealEntity mealEntity = DtoConverter.mealDtoToEntity(toUpdate);
        return DtoConverter
                .mealEntitytoDto(this.manager.updateMeal(mealEntity));
    }
    /**
     * Update menu.
     *
     * @param toUpdate
     *            the to update
     * @return the menu dto
     */
    @RequestMapping("/menu/{id}/update")
    public MenuDto updateMenu(@PathVariable int id,
            @RequestBody MenuDto toUpdate) {
        MenuEntity menuEntity = DtoConverter.menuDtotoEntity(toUpdate);
        return DtoConverter
                .menuEntitytoDto(this.manager.updateMenu(menuEntity));
    }

}
