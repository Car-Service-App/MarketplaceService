package ru.vsu.cs.zmaev.marketplaceservice.domain.dto.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класса     private final byte[] logo;\n")
public class MarketplaceCriteriaSearch {
    @Schema(description = "Id маркетплейса")
    private final Long id;
    @Schema(description = "Название маркетплейса")
    private final String name;
    @Schema(description = "URL маркетплейса")
    private final String url;
}

