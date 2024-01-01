package ru.vsu.cs.zmaev.marketplaceservice.service;

import org.springframework.data.domain.Page;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.criteria.MarketplaceCriteriaSearch;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;

public interface MarketplaceService {
    Page<MarketplaceResponseDto> findAllWithFilters(EntityPage entityPage, MarketplaceCriteriaSearch marketplaceCriteriaSearch);
    MarketplaceResponseDto findOneById(Long id);
    MarketplaceResponseDto save(MarketplaceRequestDto marketplaceRequestDto);
    MarketplaceResponseDto update(Long id, MarketplaceRequestDto marketplaceRequestDto);
    void delete(Long id);
}
