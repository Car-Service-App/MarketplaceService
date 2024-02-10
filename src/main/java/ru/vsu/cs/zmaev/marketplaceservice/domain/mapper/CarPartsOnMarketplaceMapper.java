package ru.vsu.cs.zmaev.marketplaceservice.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.CarPartOnMarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.CarPartOnMarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.CarPartOnMarketplace;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CarPartsOnMarketplaceMapper extends EntityMapper<
        CarPartOnMarketplace,
        CarPartOnMarketplaceRequestDto,
        CarPartOnMarketplaceResponseDto> {
    @Override
    CarPartOnMarketplace toEntity(CarPartOnMarketplaceRequestDto request);

    @Override
    @Mapping(source = "lastPrice", target = "lastPrice", qualifiedByName = "bigDecimalToDouble")
    CarPartOnMarketplaceResponseDto toDto(CarPartOnMarketplace entity);

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
}
