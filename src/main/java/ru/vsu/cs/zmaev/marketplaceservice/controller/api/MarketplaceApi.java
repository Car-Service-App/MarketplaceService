package ru.vsu.cs.zmaev.marketplaceservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;

import java.util.List;

@Tag(name = "Marketplace Api", description = "Api для работы с маркетплейсами")
public interface MarketplaceApi {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат маркетплейса",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MarketplaceApi.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Маркетплейса по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение маркетплейса по id")
    ResponseEntity<MarketplaceResponseDto> findOneById(@Parameter(description = "id маркетплейса") Long id);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат маркетплейсов",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MarketplaceResponseDto.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение всех маркетплейсов")
    ResponseEntity<List<MarketplaceResponseDto>> findAll();
}
