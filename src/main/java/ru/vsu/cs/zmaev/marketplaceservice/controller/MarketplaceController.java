package ru.vsu.cs.zmaev.marketplaceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.marketplaceservice.controller.api.MarketplaceApi;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.service.MarketplaceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/marketplaces")
public class MarketplaceController implements MarketplaceApi {

    private final MarketplaceService marketplaceService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<MarketplaceResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(marketplaceService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MarketplaceResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(marketplaceService.findOneById(id));
    }
}
