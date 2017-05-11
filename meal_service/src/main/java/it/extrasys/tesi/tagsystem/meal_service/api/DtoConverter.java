package it.extrasys.tesi.tagsystem.meal_service.api;

import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

/**
 * The Class DtoConverter.
 */
public class DtoConverter {

    /**
     * To dto.
     *
     * @param entity
     *            the entity
     * @return the menu dto
     */
    public static MenuDto toDto(MenuEntity entity) {
        return null;

    }

    /**
     * To dto list.
     *
     * @param menuByDate
     *            the menu by date
     * @return the list
     */
    public static List<MenuDto> toDtoList(List<MenuEntity> menuByDate) {

        List<MenuDto> menuDtos = new ArrayList<MenuDto>();
        for (MenuEntity menuEntity : menuByDate) {
            menuDtos.add(toDto(menuEntity));
        }
        return menuDtos;
    }

    /**
     * To entity.
     *
     * @param mealDto
     *            the meal dto
     * @return the meal entity
     */
    public static MealEntity toEntity(MealDto mealDto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setPrice(mealDto.getPrice());
        mealEntity.setDescription(mealDto.getDescription());
        mealEntity.setMealId(mealDto.getMealId());
        MenuDto[] menuDtos = (MenuDto[]) mealDto.getMenus().toArray();
        mealEntity.setMenus(toEntityList(menuDtos));
        return mealEntity;
    }

    /**
     * To entity.
     *
     * @param dto
     *            the dto
     * @return the menu entity
     */
    public static MenuEntity toEntity(MenuDto dto) {
        MenuEntity entity = new MenuEntity();
        entity.setDate(dto.getDate());
        MealDto[] mealDtos = (MealDto[]) dto.getMeals().toArray();
        entity.setMeals(toEntityList(mealDtos));
        return entity;

    }

    /**
     * To entity list.
     *
     * @param mealDtos
     *            the meal dtos
     * @return the list
     */
    public static List<MealEntity> toEntityList(MealDto[] mealDtos) {

        List<MealEntity> listToAdd = new ArrayList<MealEntity>();
        for (MealDto mealDto : mealDtos) {
            listToAdd.add(toEntity(mealDto));
        }
        return listToAdd;
    }
    /**
     * To entity list.
     *
     * @param menuDtos
     *            the menu dtos
     * @return the list
     */
    public static List<MenuEntity> toEntityList(MenuDto[] menuDtos) {
        List<MenuEntity> menuEntities = new ArrayList<MenuEntity>();
        for (MenuDto menuDto : menuDtos) {
            menuEntities.add(toEntity(menuDto));
        }
        return menuEntities;

    }
}
