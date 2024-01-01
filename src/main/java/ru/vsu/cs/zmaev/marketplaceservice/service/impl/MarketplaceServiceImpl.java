package ru.vsu.cs.zmaev.marketplaceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.criteria.MarketplaceCriteriaSearch;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.Marketplace;
import ru.vsu.cs.zmaev.marketplaceservice.domain.mapper.MarketplaceMapper;
import ru.vsu.cs.zmaev.marketplaceservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.marketplaceservice.repository.criteria.CriteriaRepository;
import ru.vsu.cs.zmaev.marketplaceservice.repository.jpa.MarketplaceRepository;
import ru.vsu.cs.zmaev.marketplaceservice.service.MarketplaceService;

@Service
@RequiredArgsConstructor
public class MarketplaceServiceImpl implements MarketplaceService {

    private final MarketplaceRepository marketplaceRepository;
    private final CriteriaRepository<Marketplace, MarketplaceCriteriaSearch> marketplaceCriteriaRepository;
    private final MarketplaceMapper marketplaceMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<MarketplaceResponseDto> findAllWithFilters(EntityPage entityPage, MarketplaceCriteriaSearch marketplaceCriteriaSearch) {
        return marketplaceCriteriaRepository.findAllWithFilters(entityPage, marketplaceCriteriaSearch).map(marketplaceMapper::toDto);
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
