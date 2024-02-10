package ru.vsu.cs.zmaev.marketplaceservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class CarPartOnMarketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "marketplace_id", referencedColumnName = "id")
    private Marketplace marketplace;

    @Column(name = "is_original")
    private Boolean isOriginal;

    @Column(name = "car_part_id")
    private Long carPartId;

    @Column(name = "last_price")
    private BigDecimal lastPrice;

    @Column(name = "url")
    private String url;
}
