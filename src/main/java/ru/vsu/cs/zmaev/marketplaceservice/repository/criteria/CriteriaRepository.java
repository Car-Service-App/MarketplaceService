package ru.vsu.cs.zmaev.marketplaceservice.repository.criteria;

import org.springframework.data.domain.Page;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.EntityPage;

public interface CriteriaRepository<E, C> {

    Page<E> findAllWithFilters(EntityPage entityPage, C criteriaSearch);

}
