package ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class MarketplaceFindAllRequestDto {
    @Schema(description = "Название маркетплейса")
    private final String name;
    @Schema(description = "URL маркетплейса")
    private final String url;
    @Schema(description = "Лого маркетплейса")
    private final String logo;
    private int pageNumber;
    private int pageSize;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "id";
}
