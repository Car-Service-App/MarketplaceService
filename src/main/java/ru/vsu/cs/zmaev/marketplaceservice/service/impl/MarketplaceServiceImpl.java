package ru.vsu.cs.zmaev.marketplaceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.Marketplace;
import ru.vsu.cs.zmaev.marketplaceservice.domain.mapper.MarketplaceMapper;
import ru.vsu.cs.zmaev.marketplaceservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.marketplaceservice.repository.jpa.MarketplaceRepository;
import ru.vsu.cs.zmaev.marketplaceservice.service.MarketplaceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketplaceServiceImpl implements MarketplaceService {

    private final MarketplaceRepository marketplaceRepository;
    private final MarketplaceMapper marketplaceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MarketplaceResponseDto> findAll() {
        return marketplaceRepository.findAll().stream().map(marketplaceMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MarketplaceResponseDto findOneById(Long id) {
        Marketplace marketplace = marketplaceRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(Marketplace.class, id));
        return marketplaceMapper.toDto(marketplace);
    }

    @Override
    @Transactional
    public MarketplaceResponseDto save(MarketplaceRequestDto marketplaceRequestDto) {
        Marketplace marketplace = marketplaceMapper.toEntity(marketplaceRequestDto);
        return marketplaceMapper.toDto(marketplaceRepository.save(marketplace));
    }

    @Override
    @Transactional
    public MarketplaceResponseDto update(Long id, MarketplaceRequestDto marketplaceRequestDto) {
        return marketplaceRepository
                .findById(id)
                .map(existingEvent -> {
                    marketplaceMapper.partialUpdate(existingEvent, marketplaceRequestDto);
                    return existingEvent;
                })
                .map(marketplaceRepository::save)
                .map(marketplaceMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(Marketplace.class, id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Marketplace marketplace = marketplaceRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(Marketplace.class, id));
        marketplaceRepository.delete(marketplace);
    }
}
