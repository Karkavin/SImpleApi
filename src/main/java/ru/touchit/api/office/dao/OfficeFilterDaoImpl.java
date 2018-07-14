package ru.touchit.api.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.touchit.api.office.model.Office;
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
public class OfficeFilterDaoImpl implements OfficeFilterDao {
    private final EntityManager entityManager;

    /**
     * Конструктор
     * @param entityManager для управления entity-объектами
     */
    @Autowired
    public OfficeFilterDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> filter(Organisation organisation, String name, String phone, Boolean isActive) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);

        Predicate predicateOrganisation = criteriaBuilder.equal(root.get("organisation"), organisation);
        Predicate predicateName;
        if (name == null) {
            predicateName = criteriaBuilder.conjunction();
        } else {
            predicateName = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        }
        Predicate predicatePhone;
        if (phone == null) {
            predicatePhone = criteriaBuilder.conjunction();
        } else {
            predicatePhone = criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
        }
        Predicate predicateIsActive;
        if (isActive == null) {
            predicateIsActive = criteriaBuilder.conjunction();
        } else {
            predicateIsActive = criteriaBuilder.equal(root.get("isActive"), isActive);
        }

        criteriaQuery.where(criteriaBuilder.and(predicateOrganisation, predicateName, predicatePhone, predicateIsActive));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}