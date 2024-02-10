package ru.vsu.cs.zmaev.marketplaceservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.CarPartOnMarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.CarPartOnMarketplaceResponseDto;

@Tag(name = "CarPartOnMarketplace Api", description = "Api для работы с запчастями на маркеплейсах")
public interface CarPartOnMarketplaceApi {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат запчасти из маркетплейса",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartOnMarketplaceResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Запчасти из маркетплейса по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение зетали из маркетплейса по id")
    ResponseEntity<CarPartOnMarketplaceResponseDto> findOneById(@Parameter(description = "id зетали на маркетплейсе") Long id);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат маркетплейсов",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartOnMarketplaceResponseDto.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение всех маркетплейсов")
    ResponseEntity<Page<CarPartOnMarketplaceResponseDto>> findAll(
            @Parameter(description = "Начальный номер страницы") @RequestParam(defaultValue = "0") Integer pageNumber,
            @Parameter(description = "Размер старницы") @RequestParam(defaultValue = "10") Integer pageSize);



    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное добавление запчасти",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartOnMarketplaceResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "По переданному id не существует сущости",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    ResponseEntity<CarPartOnMarketplaceResponseDto> save(@Parameter(description = "Dto запроса") @RequestBody CarPartOnMarketplaceRequestDto dto);
}
