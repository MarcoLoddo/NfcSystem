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
     * To entity list.
     *
     * @param mealDtos
     *            the meal dtos
     * @return the list
     */
    public static List<MealEntity> mealDtotoEntityList(List<MealDto> mealDtos) {

        List<MealEntity> listToAdd = new ArrayList<MealEntity>();
        for (MealDto mealDto : mealDtos) {
            listToAdd.add(mealDtoToEntity(mealDto));
        }
        return listToAdd;
    }

    /**
     * To dto.
     *
     * @param mealEntity
     *            the meal entity
     * @return the meal dto
     */
    public static MealDto mealEntitytoDto(MealEntity mealEntity) {
        MealDto mealDto = new MealDto();
        mealDto.setDescription(mealEntity.getDescription());
        mealDto.setMealId(mealEntity.getMealId());
        mealDto.setPrice(mealEntity.getPrice());
        mealDto.setType(mealEntity.getType());
        return mealDto;
    }

    /**
     * To dto list.
     *
     * @param list
     *            the meals
     * @return the list
     */
    public static List<MealDto> mealEntitytoDtoList(List<MealEntity> list) {
        List<MealDto> mealDtos = new ArrayList<MealDto>();
        for (MealEntity mealDto : list) {
            mealDtos.add(mealEntitytoDto(mealDto));
        }
        return mealDtos;
    }

    /**
     * To entity.
     *
     * @param dto
     *            the dto
     * @return the menu entity
     */
    public static MenuEntity menuDtotoEntity(MenuDto dto) {
        MenuEntity entity = new MenuEntity();
        entity.setDate(dto.getDate());
        entity.setType(dto.getType());
        return entity;

    }

    /**
     * To entity list.
     *
     * @param menuDtos
     *            the menu dtos
     * @return the list
     */
    public static List<MenuEntity> menuDtotoEntityList(List<MenuDto> menuDtos) {
        List<MenuEntity> menuEntities = new ArrayList<MenuEntity>();
        for (MenuDto menuDto : menuDtos) {
            menuEntities.add(menuDtotoEntity(menuDto));
        }
        return menuEntities;

    }

    /**
     * To dto.
     *
     * @param entity
     *            the entity
     * @return the menu dto
     */
    public static MenuDto menuEntitytoDto(MenuEntity entity) {
        MenuDto menuDto = new MenuDto();
        menuDto.setDate(entity.getDate());
        menuDto.setMenuId(entity.getMenuId());
        menuDto.setType(entity.getType());
        menuDto.setMeals(new ArrayList<MealDto>());
        for (MealEntity meal : entity.getMeals()) {
            menuDto.getMeals().add(mealEntitytoDto(meal));
        }
        return menuDto;

    }

    /**
     * To dto list.
     *
     * @param menuByDate
     *            the menu by date
     * @return the list
     */
    public static List<MenuDto> menuEntitytoDtoList(
            List<MenuEntity> menuByDate) {

        List<MenuDto> menuDtos = new ArrayList<MenuDto>();
        for (MenuEntity menuEntity : menuByDate) {
            menuDtos.add(menuEntitytoDto(menuEntity));
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
    public static MealEntity mealDtoToEntity(MealDto mealDto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setPrice(mealDto.getPrice());
        mealEntity.setDescription(mealDto.getDescription());
        mealEntity.setMealId(mealDto.getMealId());
        mealEntity.setMenus(new ArrayList<MenuEntity>());
        mealEntity.setType(mealDto.getType());
        return mealEntity;
    }
}
