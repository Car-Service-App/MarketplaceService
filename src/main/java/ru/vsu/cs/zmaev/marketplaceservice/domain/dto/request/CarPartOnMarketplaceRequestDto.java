package ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request;

import lombok.Data;

@Data
public class CarPartOnMarketplaceRequestDto {
    private final Long id;
    private final Long marketplaceId;
    private final Long carPartId;
    private final Double lastPrice;
    private final String url;
}
