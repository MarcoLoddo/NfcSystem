package it.extrasys.tesi.tagsystem.meal_app.client;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
public class RestClient {

    /** The rest template. */
    private RestTemplate restTemplate;

    /**
     * Instantiates a new rest client.
     */
    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Adds the menu.
     *
     * @param menuDto
     *            the menu dto
     * @return the menu dto
     */
    public MenuDto addMenu(MenuDto menuDto) {
        String uri = Messages.get("add.menu");
        MenuDto menuPersisted = this.restTemplate
                .postForEntity(uri, menuDto, MenuDto.class).getBody();

        uri = Messages.get("add.meal.to.menu");
        Map<String, Long> map = new HashMap<>();
        for (MealDto mealDto : menuDto.getMeals()) {
            map.clear();
            map.put("menuId", menuPersisted.getMenuId());
            map.put("mealId", mealDto.getMealId());
            this.restTemplate.getForEntity(uri, null, map);
        }
        return menuPersisted;
    }

    /**
     * Gets the menu by day.
     *
     * @param date
     *            the date
     * @return the menu by day
     */
    public List<MenuDto> getMenuByDay(Date date) {
        String uri = Messages.get("get.menu.by.day");
        Map<String, Date> map = new HashMap<>();
        map.put("date", date);
        MenuDto[] menuDtos = this.restTemplate
                .getForEntity(uri, MenuDto[].class, map).getBody();

        return Arrays.asList(menuDtos);

    }

    /**
     * Gets the menu.
     *
     * @param id
     *            the id
     * @return the menu
     */
    public MenuDto getMenu(long id) {
        String uri = Messages.get("get.menu");
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return this.restTemplate.getForEntity(uri, MenuDto.class, map)
                .getBody();
    }

    /**
     * Gets the all meals.
     *
     * @return the all meals
     */
    public MealDto[] getAllMeals() {

        String uri = Messages.get("get.all.meals");
        return this.restTemplate.getForEntity(uri, MealDto[].class).getBody();
    }

    /**
     * Update menu.
     *
     * @param menu
     *            the menu
     * @return the menu dto
     */
    public MenuDto updateMenu(MenuDto menu) {
        String uri = Messages.get("update.menu");
        this.restTemplate.put(uri, menu);
        addMealsToMenu(menu);
        return menu;
    }

    /**
     * Adds the meals to menu.
     *
     * @param menu
     *            the menu
     */
    public void addMealsToMenu(MenuDto menu) {
        Map<String, Long> map = new HashMap<>();
        String uri = Messages.get("add.meal.to.menu");

        for (MealDto mealDto : menu.getMeals()) {
            map.clear();
            map.put("menuId", menu.getMenuId());
            this.restTemplate.postForEntity(uri, mealDto, null, map);
        }
    }

    /**
     * Update meal.
     *
     * @param updatedMeal
     *            the updated meal
     */
    public void updateMeal(MealDto updatedMeal) {

        String uri = Messages.get("update.meal");

        this.restTemplate.put(uri, updatedMeal);

    }

}
