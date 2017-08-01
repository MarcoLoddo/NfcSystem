package it.extrasys.tesi.tagsystem.integrity_test_service.restclients;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.integrity_test_service.Messages;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MenuDto;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
@Component
public class MealRestClientImpl implements MealRestClient {

    /** The rest template. */
    private RestTemplate restTemplate;

    @Autowired
    private Messages messages;
    /**
     * Instantiates a new rest client.
     */
    public MealRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#addMenu(it.extrasys.tesi.tagsystem.integrity_test_service.
     * api.meals.MenuDto)
     */
    @Override
    public MenuDto addMenu(MenuDto menuDto) {
        String uri = this.messages.getMessages("add.menu");
        MenuDto menuPersisted = this.restTemplate
                .postForEntity(uri, menuDto, MenuDto.class).getBody();

        menuDto.setMenuId(menuPersisted.getMenuId());
        addMealsToMenu(menuDto);
        return getMenu(menuDto.getMenuId());
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#getMenuByDay(java.util.Date)
     */
    @Override
    public List<MenuDto> getMenuByDay(Date date) {
        String uri = this.messages.getMessages("get.menu.by.day");
        Map<String, Date> map = new HashMap<>();
        map.put("date", date);
        MenuDto[] menuDtos = this.restTemplate
                .getForEntity(uri, MenuDto[].class, map).getBody();

        return Arrays.asList(menuDtos);

    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#getMenu(long)
     */
    @Override
    public MenuDto getMenu(long id) {
        String uri = this.messages.getMessages("get.menu");
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return this.restTemplate.getForEntity(uri, MenuDto.class, map)
                .getBody();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#getAllMeals()
     */
    @Override
    public MealDto[] getAllMeals() {

        String uri = this.messages.getMessages("get.all.meals");
        return this.restTemplate.getForEntity(uri, MealDto[].class).getBody();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#updateMenu(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.meals.MenuDto)
     */
    @Override
    public MenuDto updateMenu(MenuDto menu) {
        String uri = this.messages.getMessages("update.menu");
        this.restTemplate.put(uri, menu);
        addMealsToMenu(menu);
        return getMenu(menu.getMenuId());
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#addMealsToMenu(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.meals.MenuDto)
     */
    @Override
    public void addMealsToMenu(MenuDto menu) {

        for (MealDto mealDto : menu.getMeals()) {
            addMealToMenu(mealDto, menu);

        }
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#addMealToMenu(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.meals.MealDto,
     * it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MenuDto)
     */
    @Override
    public MenuDto addMealToMenu(MealDto mealDto, MenuDto menuDto) {
        Map<String, Long> map = new HashMap<>();
        String uri = this.messages.getMessages("add.meal.to.menu");
        map.put("menuId", menuDto.getMenuId());
        map.put("mealId", mealDto.getMealId());
        this.restTemplate.put(uri, mealDto, map);
        return getMenu(menuDto.getMenuId());
    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#updateMeal(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.meals.MealDto)
     */
    @Override
    public void updateMeal(MealDto updatedMeal) {

        String uri = this.messages.getMessages("update.meal");

        this.restTemplate.put(uri, updatedMeal);

    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * MealRestClient#addMeal(it.extrasys.tesi.tagsystem.integrity_test_service.
     * api.meals.MealDto)
     */
    @Override
    public MealDto addMeal(MealDto mealDto) {
        String uri = this.messages.getMessages("add.meal");
        return this.restTemplate.postForEntity(uri, mealDto, MealDto.class)
                .getBody();
    }

}