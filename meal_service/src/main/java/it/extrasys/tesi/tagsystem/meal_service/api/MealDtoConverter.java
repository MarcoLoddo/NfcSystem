package it.extrasys.tesi.tagsystem.meal_service.api;

import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;

/**
 * The Class DtoConverter.
 */
public final class MealDtoConverter {

    private MealDtoConverter() {

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
        mealEntity.setType(mealDto.getType());
        return mealEntity;
    }

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

}
