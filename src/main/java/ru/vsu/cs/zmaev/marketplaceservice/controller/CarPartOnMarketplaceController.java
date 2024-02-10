package ru.vsu.cs.zmaev.marketplaceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.marketplaceservice.controller.api.CarPartOnMarketplaceApi;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.CarPartOnMarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.CarPartOnMarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.service.CarPartOnMarketplaceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/car-part-on-marketplace")
public class CarPartOnMarketplaceController implements CarPartOnMarketplaceApi {

    private final CarPartOnMarketplaceService carPartOnMarketplaceService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CarPartOnMarketplaceResponseDto>> findAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartOnMarketplaceService.findAll(pageable));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartOnMarketplaceResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartOnMarketplaceService.findOneById(id));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<CarPartOnMarketplaceResponseDto> save(@RequestBody CarPartOnMarketplaceRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartOnMarketplaceService.save(dto));
    }
}
