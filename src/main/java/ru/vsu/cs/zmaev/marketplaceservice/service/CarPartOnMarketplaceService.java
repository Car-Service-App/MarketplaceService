package ru.vsu.cs.zmaev.marketplaceservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.CarPartOnMarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.CarPartOnMarketplaceResponseDto;

public interface CarPartOnMarketplaceService {
    Page<CarPartOnMarketplaceResponseDto> findAll(Pageable pageable);
    CarPartOnMarketplaceResponseDto findOneById(Long id);
    CarPartOnMarketplaceResponseDto save(CarPartOnMarketplaceRequestDto dto);
}
