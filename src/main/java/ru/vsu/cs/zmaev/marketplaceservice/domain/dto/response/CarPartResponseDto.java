package ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класса CarPartCriteriaSearch")
public class CarPartResponseDto {
    @Schema(description = "Id детали")
    private final Long id;
    @Schema(description = "Id автомобиля")
    private final Long carId;
    @Schema(description = "Id производителя")
    private final Long manufacturerId;
    @Schema(description = "Название категории")
    private String carPartType;
    @Schema(description = "Название детали")
    private final String name;
    @Schema(description = "OEM номер детали")
    private final String oem;
    @Schema(description = "Средняя цена детали")
    private final Double price;
    @Schema(description = "Описание детали")
    private final String description;
    @Schema(description = "Ссылка на изображение детали")
    private final String carPartImage;
}
