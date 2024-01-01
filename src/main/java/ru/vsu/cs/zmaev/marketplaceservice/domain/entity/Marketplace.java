package ru.vsu.cs.zmaev.marketplaceservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Marketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(mappedBy = "marketplace")
    private List<CarPartOnMarketplace> carPartsOnMarketplace;
}
