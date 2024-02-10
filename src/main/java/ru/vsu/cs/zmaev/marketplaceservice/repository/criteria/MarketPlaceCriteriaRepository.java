package ru.vsu.cs.zmaev.marketplaceservice.repository.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.marketplaceservice.domain.dto.criteria.MarketplaceCriteriaSearch;
import ru.vsu.cs.zmaev.marketplaceservice.domain.entity.Marketplace;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MarketPlaceCriteriaRepository extends AbstractCriteriaRepository<Marketplace, MarketplaceCriteriaSearch> {

    protected MarketPlaceCriteriaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Marketplace> getEntityClass() {
        return Marketplace.class;
    }

    @Override
    protected Predicate getPredicate(MarketplaceCriteriaSearch searchCriteria, Root<Marketplace> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getName())) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchCriteria.getName()+ "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected long getCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Marketplace> countRoot = countQuery.from(Marketplace.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
