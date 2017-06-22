package it.extrasys.tesi.tagsystem.corner_service.api;

import java.util.List;
import java.util.stream.Collectors;

import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.CornerEntity;

public final class CornerDtoConverter {
    private CornerDtoConverter() {

    }

    public static CornerDto toDto(CornerEntity corner) {

        CornerDto dto = new CornerDto();
        dto.setCornerId(corner.getCornerId());
        dto.setMealId(corner.getMealId());
        dto.setReader(NfcReaderDtoConverter.toDto(corner.getReader()));
        return dto;
    }
    public static List<CornerDto> toDtoList(List<CornerEntity> corners) {
        return corners.stream().map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }
    public static CornerEntity toEntity(CornerDto cornerDto) {
        CornerEntity corner = new CornerEntity();
        corner.setCornerId(cornerDto.getCornerId());
        corner.setMealId(cornerDto.getMealId());
        corner.setReader(NfcReaderDtoConverter.toEntity(cornerDto.getReader()));
        return corner;
    }
}
