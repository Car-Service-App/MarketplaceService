package ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response;

import lombok.Data;

@Data
public class CarPartOnMarketplaceResponseDto {
    private final Long id;
    private final MarketplaceResponseDto marketplace;
    private final CarPartResponseDto carPart;
    private final Boolean isOriginal;
    private final Double lastPrice;
    private final String url;
}
