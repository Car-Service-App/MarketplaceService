package ru.vsu.cs.zmaev.marketplaceservice.service;

import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;

import java.util.List;

public interface MarketplaceService {
    List<MarketplaceResponseDto> findAll();
    MarketplaceResponseDto findOneById(Long id);
    MarketplaceResponseDto save(MarketplaceRequestDto marketplaceRequestDto);
    MarketplaceResponseDto update(Long id, MarketplaceRequestDto marketplaceRequestDto);
    void delete(Long id);
}
