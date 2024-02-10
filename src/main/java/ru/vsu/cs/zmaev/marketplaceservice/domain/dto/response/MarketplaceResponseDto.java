package ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класса MarketplaceRequestDto")
public class MarketplaceResponseDto {
    @Schema(description = "Id маркетплейса")
    private final Long id;
    @Schema(description = "Название маркетплейса")
    private final String name;
    @Schema(description = "URL маркетплейса")
    private final String url;
    @Schema(description = "Лого маркетплейса")
    private final String logo;
}
