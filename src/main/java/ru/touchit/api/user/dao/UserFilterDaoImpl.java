package ru.touchit.api.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.touchit.api.catalog.model.Country;
import ru.touchit.api.catalog.model.Doc;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.user.model.User;

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
public class UserFilterDaoImpl implements UserFilterDao {
    private final EntityManager entityManager;

    /**
     * Конструктор
     * @param entityManager для управления entity-объектами
     */
    @Autowired
    public UserFilterDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> filter(Office office, String firstName, String secondName,
                             String middleName, String position, Doc doc, Country country) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate predicateOffice = criteriaBuilder.equal(root.get("office"), office);
        Predicate predicateFirstName;
        if (firstName == null) {
            predicateFirstName = criteriaBuilder.conjunction();
        } else {
            predicateFirstName = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        }
        Predicate predicateSecondName;
        if (secondName == null) {
            predicateSecondName = criteriaBuilder.conjunction();
        } else {
            predicateSecondName = criteriaBuilder.like(criteriaBuilder.lower(root.get("secondName")), "%" + secondName.toLowerCase() + "%");
        }
        Predicate predicateMiddleName;
        if (middleName == null) {
            predicateMiddleName = criteriaBuilder.conjunction();
        } else {
            predicateMiddleName = criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")), "%" + middleName.toLowerCase() + "%");
        }
        Predicate predicatePosition;
        if (position == null) {
            predicatePosition = criteriaBuilder.conjunction();
        } else {
            predicatePosition = criteriaBuilder.like(criteriaBuilder.lower(root.get("position")), "%" + position.toLowerCase() + "%");
        }
        Predicate predicateDoc;
        if (doc == null) {
            predicateDoc = criteriaBuilder.conjunction();
        } else {
            predicateDoc = criteriaBuilder.equal(root.get("doc"), doc);
        }
        Predicate predicateCountry;
        if (country == null) {
            predicateCountry = criteriaBuilder.conjunction();
        } else {
            predicateCountry = criteriaBuilder.equal(root.get("country"), country);
        }

        criteriaQuery.where(criteriaBuilder.and(predicateOffice, predicateFirstName, predicateSecondName,
                predicateMiddleName, predicatePosition, predicateDoc, predicateCountry));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}