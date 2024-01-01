package ru.vsu.cs.zmaev.marketplaceservice.domain.mapper;

import org.mapstruct.Mapper;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.Marketplace;

@Mapper(componentModel = "spring")
public interface MarketplaceMapper extends EntityMapper<Marketplace, MarketplaceRequestDto, MarketplaceResponseDto> {
    @Override
    Marketplace toEntity(MarketplaceRequestDto request);

    @Override
    MarketplaceResponseDto toDto(Marketplace entity);
}
