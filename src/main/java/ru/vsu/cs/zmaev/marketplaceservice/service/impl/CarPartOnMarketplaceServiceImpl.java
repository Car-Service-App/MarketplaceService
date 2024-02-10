package ru.vsu.cs.zmaev.marketplaceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.CarPartOnMarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.CarPartOnMarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.CarPartOnMarketplace;
import ru.vsu.cs.zmaev.marketplaceservice.domain.mapper.CarPartsOnMarketplaceMapper;
import ru.vsu.cs.zmaev.marketplaceservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.marketplaceservice.repository.jpa.CarPartOnMarketplaceRepository;
import ru.vsu.cs.zmaev.marketplaceservice.service.CarPartOnMarketplaceService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarPartOnMarketplaceServiceImpl implements CarPartOnMarketplaceService {

    private final CarPartOnMarketplaceRepository carPartOnMarketplaceRepository;
    private final CarPartsOnMarketplaceMapper carPartsOnMarketplaceMapper;

    private final WebClient webClient;

    @Value("${car-parts.service.base-url}")
    private String carServiceBaseUrl;

    @Override
    @Transactional(readOnly = true)
    public Page<CarPartOnMarketplaceResponseDto> findAll(Pageable pageable) {
        Page<CarPartOnMarketplace> carPartOnMarketplaces = carPartOnMarketplaceRepository.findAll(pageable);
        return carPartOnMarketplaces.stream()
                .map(carPartOnMarketplace -> {
                    CarPartResponseDto carPartResponseDto = findCarPartByIdSync(carPartOnMarketplace.getCarPartId());
                    return new CarPartOnMarketplaceResponseDto(
                            carPartOnMarketplace.getId(),
                            new MarketplaceResponseDto(
                                    carPartOnMarketplace.getMarketplace().getId(),
                                    carPartOnMarketplace.getMarketplace().getName(),
                                    carPartOnMarketplace.getMarketplace().getUrl(),
                                    carPartOnMarketplace.getMarketplace().getLogo()),
                            carPartResponseDto,
                            carPartOnMarketplace.getIsOriginal(),
                            carPartOnMarketplace.getLastPrice().doubleValue(),
                            carPartOnMarketplace.getUrl()
                    );
                }).collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> new PageImpl<>(list, pageable, list.size())
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public CarPartOnMarketplaceResponseDto findOneById(Long id) {
        CarPartOnMarketplace carPartOnMarketplace =
                carPartOnMarketplaceRepository.findById(id).orElseThrow(() ->
                        new NoSuchEntityException(CarPartOnMarketplace.class, id));
        return carPartsOnMarketplaceMapper.toDto(carPartOnMarketplace);
    }

    @Override
    @Transactional
    public CarPartOnMarketplaceResponseDto save(CarPartOnMarketplaceRequestDto dto) {
        CarPartOnMarketplace carPartOnMarketplace = carPartsOnMarketplaceMapper.toEntity(dto);
        carPartOnMarketplace = carPartOnMarketplaceRepository.save(carPartOnMarketplace);
        return carPartsOnMarketplaceMapper.toDto(carPartOnMarketplace);
    }

    private CarPartResponseDto findCarPartByIdSync(final Long id) {
        return webClient
                .get()
                .uri(String.format("%s/api/car-parts/string-name/{id}", carServiceBaseUrl), id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NoSuchEntityException(CarPartResponseDto.class, id));
                    } else {
                        return Mono.error(new RuntimeException("Client error"));
                    }
                })
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("Server error")))
                .bodyToMono(CarPartResponseDto.class)
                .block();
    }
}
