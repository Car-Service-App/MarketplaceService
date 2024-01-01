package ru.vsu.cs.zmaev.marketplaceservice.domain.mapper;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.request.MarketplaceRequestDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.response.MarketplaceResponseDto;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.Marketplace;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-01T11:21:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class MarketplaceMapperImpl implements MarketplaceMapper {

    @Override
    public MarketplaceResponseDto toResponse(MarketplaceRequestDto request) {
        if ( request == null ) {
            return null;
        }

        String name = null;
        String url = null;
        byte[] logo = null;

        name = request.getName();
        url = request.getUrl();
        byte[] logo1 = request.getLogo();
        if ( logo1 != null ) {
            logo = Arrays.copyOf( logo1, logo1.length );
        }

        Long id = null;

        MarketplaceResponseDto marketplaceResponseDto = new MarketplaceResponseDto( id, name, url, logo );

        return marketplaceResponseDto;
    }

    @Override
    public void partialUpdate(Marketplace entity, MarketplaceRequestDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getUrl() != null ) {
            entity.setUrl( dto.getUrl() );
        }
        byte[] logo = dto.getLogo();
        if ( logo != null ) {
            entity.setLogo( Arrays.copyOf( logo, logo.length ) );
        }
    }

    @Override
    public Marketplace toEntity(MarketplaceRequestDto request) {
        if ( request == null ) {
            return null;
        }

        Marketplace marketplace = new Marketplace();

        marketplace.setName( request.getName() );
        marketplace.setUrl( request.getUrl() );
        byte[] logo = request.getLogo();
        if ( logo != null ) {
            marketplace.setLogo( Arrays.copyOf( logo, logo.length ) );
        }

        return marketplace;
    }

    @Override
    public MarketplaceResponseDto toDto(Marketplace entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String url = null;
        byte[] logo = null;

        id = entity.getId();
        name = entity.getName();
        url = entity.getUrl();
        byte[] logo1 = entity.getLogo();
        if ( logo1 != null ) {
            logo = Arrays.copyOf( logo1, logo1.length );
        }

        MarketplaceResponseDto marketplaceResponseDto = new MarketplaceResponseDto( id, name, url, logo );

        return marketplaceResponseDto;
    }
}
