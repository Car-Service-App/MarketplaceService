package ru.vsu.cs.zmaev.marketplaceservice.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.CarPartOnMarketplace;

@Repository
public interface CarPartOnMarketplaceRepository extends JpaRepository<CarPartOnMarketplace, Long> {
    Page<CarPartOnMarketplace> findAll(Pageable pageable);
}
