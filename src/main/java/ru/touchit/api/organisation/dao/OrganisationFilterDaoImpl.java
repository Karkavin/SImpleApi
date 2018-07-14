package ru.touchit.api.organisation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.touchit.api.organisation.model.Organisation;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 * @author Artyom Karkavin
 */
@Repository
public class OrganisationFilterDaoImpl implements OrganisationFilterDao {
    private final EntityManager entityManager;

    /**
     * Конструктор
     * @param entityManager для управления entity-объектами
     */
    @Autowired
    public OrganisationFilterDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organisation> filter(String name, String inn, Boolean isActive) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organisation> criteriaQuery = criteriaBuilder.createQuery(Organisation.class);
        Root<Organisation> root = criteriaQuery.from(Organisation.class);

        Predicate predicateName = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        Predicate predicateInn;
        if (inn == null) {
            predicateInn = criteriaBuilder.conjunction();
        } else {
            predicateInn = criteriaBuilder.like(root.get("inn"), "%" + inn + "%");
        }
        Predicate predicateIsActive;
        if (isActive == null) {
            predicateIsActive = criteriaBuilder.conjunction();
        } else {
            predicateIsActive = criteriaBuilder.equal(root.get("isActive"), isActive);
        }

        criteriaQuery.where(criteriaBuilder.and(predicateName, predicateInn, predicateIsActive));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}