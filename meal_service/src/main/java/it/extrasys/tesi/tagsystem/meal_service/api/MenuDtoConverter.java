package it.extrasys.tesi.tagsystem.meal_service.api;

import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

/**
 * The Class MenuDtoConverter.
 */
public final class MenuDtoConverter {
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
        entity.setMenuId(dto.getMenuId());
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
            menuDto.getMeals().add(MealDtoConverter.mealEntitytoDto(meal));
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
}
