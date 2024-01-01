package ru.vsu.cs.zmaev.marketplaceservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.criteria.MarketplaceCriteriaSearch;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.service.MarketplaceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/marketplaces")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MarketplaceResponseDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String url,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        EntityPage entityPage =
                new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        MarketplaceCriteriaSearch criteriaSearch =
                new MarketplaceCriteriaSearch(
                        0L,
                        name,
                        url);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(marketplaceService.findAllWithFilters(entityPage, criteriaSearch));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MarketplaceResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(marketplaceService.findOneById(id));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<MarketplaceResponseDto> create(@Valid @RequestBody MarketplaceRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(marketplaceService.save(requestDto));
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MarketplaceResponseDto> update(@PathVariable Long id, @Valid @RequestBody MarketplaceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(marketplaceService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        marketplaceService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
