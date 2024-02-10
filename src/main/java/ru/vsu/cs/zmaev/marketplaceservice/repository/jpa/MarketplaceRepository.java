package ru.vsu.cs.zmaev.marketplaceservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.Marketplace;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
}
