package it.extrasys.tesi.tagsystem.meal_service.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.meal_service.api.MealDto;
import it.extrasys.tesi.tagsystem.meal_service.api.MealDtoConverter;
import it.extrasys.tesi.tagsystem.meal_service.api.MenuDto;
import it.extrasys.tesi.tagsystem.meal_service.api.MenuDtoConverter;
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
    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public MealDto addMeal(@RequestBody MealDto meal) {
        return MealDtoConverter.mealEntitytoDto(
                this.manager.addMeal(MealDtoConverter.mealDtoToEntity(meal)));
    }

    /**
     * Adds the mealto menu.
     *
     * @param menuId
     *            the menu id
     */
    @RequestMapping(value = "/menus/{menuId}/meals", method = RequestMethod.POST)

    public void addMealtoMenu(@PathVariable Long menuId,
            @RequestBody MealDto mealDto) {
        MealEntity mealEntity = this.manager.getMeal(mealDto.getMealId());
        MenuEntity menuEntity = this.manager.getMenu(menuId);
        if (!menuEntity.getMeals().stream()
                .anyMatch(e -> e.getMealId() == mealDto.getMealId())) {
            mealEntity.addToMenu(menuEntity);
            this.manager.updateMeal(mealEntity);
        }
    }
    /**
     * Adds the menu.
     */
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public MenuDto addMenu(@RequestBody MenuDto menu) {
        MenuEntity menuEntity = MenuDtoConverter.menuDtotoEntity(menu);
        return MenuDtoConverter
                .menuEntitytoDto(this.manager.addMenu(menuEntity));

    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public List<MealDto> getAllMeals() {

        return MealDtoConverter.mealEntitytoDtoList(this.manager.getAllMeal());
    }

    /**
     * Gets the menu by id.
     *
     * @param menuId
     *            the id
     * @return the menus of day
     */
    @RequestMapping(value = "/menus/{menuId}", method = RequestMethod.GET)
    public MenuDto getMenuById(@PathVariable Long menuId) {

        return MenuDtoConverter.menuEntitytoDto(this.manager.getMenu(menuId));
    }
    /**
     * Gets the menus of day.
     *
     * @return the menus of day
     */
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public List<MenuDto> getMenusOfDay(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date day) {
        List<MenuDto> dtos = MenuDtoConverter
                .menuEntitytoDtoList(this.manager.getMenuByDate(day));
        return dtos;
    }

    /**
     * Update meal.
     *
     * @param toUpdate
     *            the to update
     */
    @RequestMapping(value = "/meals", method = RequestMethod.PUT)
    public void updateMeal(@RequestBody MealDto toUpdate) {
        MealEntity mealEntity = this.manager.getMeal(toUpdate.getMealId());
        mealEntity.setDescription(toUpdate.getDescription());
        mealEntity.setPrice(toUpdate.getPrice());
        mealEntity.setType(toUpdate.getType());
        this.manager.updateMeal(mealEntity);
    }
    /**
     * Update menu.
     *
     * @param toUpdate
     *            the to update
     */
    @RequestMapping(value = "/menus", method = RequestMethod.PUT)
    public void updateMenu(@RequestBody MenuDto toUpdate) {
        MenuEntity menuEntity = this.manager.getMenu(toUpdate.getMenuId());
        menuEntity.setDate(toUpdate.getDate());
        menuEntity.setType(toUpdate.getType());
        this.manager.updateMenu(menuEntity);
    }

}
