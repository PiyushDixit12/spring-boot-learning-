package com.criteriaquery.CriteriaQuery.dao;

import com.criteriaquery.CriteriaQuery.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    EntityManager entityManager;

    public List<Product> findProductsByNameAndCategory(String productName, String productCategory) {

//        create object of Criteria Builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

//        create a CriteriaQuery from criteria builder object
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);


        Root<Product> productRoot = cq.from(Product.class);

        Predicate productNamePredicate = cb.like(productRoot.get("name"), "%" + productName + "%");

        Predicate productCategoryPredicate = cb.equal(productRoot.get("category"), productCategory);

        cq.where(productNamePredicate, productCategoryPredicate);

        TypedQuery<Product> query = entityManager.createQuery(cq);

        return query.getResultList();

    }

    public List<Product> filterProducts(String productName, String category, Double minValue, Double maxValue) {
        // create object of Criteria Builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);

        Root<Product> productRoot = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (productName != null && !productName.isEmpty()) {
            Predicate productNamePredicate = cb.like(productRoot.get("name"), "%" + productName + "%");
            predicates.add(productNamePredicate);
        }

        if (category != null && !category.isEmpty()) {
            Predicate categoryPredicate = cb.equal(productRoot.get("category"), category);
            predicates.add(categoryPredicate);
        }

        if (minValue != null) {
            Predicate minPricePredicate = cb.greaterThanOrEqualTo(productRoot.get("price"), minValue);
            predicates.add(minPricePredicate);
        }
        if (maxValue != null) {
            Predicate maxPricePredicate = cb.lessThanOrEqualTo(productRoot.get("price"), maxValue);
            predicates.add(maxPricePredicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    public List<Product> getAllProductsOnRangeOfId(int minId , int maxId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> productRoot= criteriaQuery.from(Product.class);

        Predicate idPredicate = criteriaBuilder.between(productRoot.get("id"), minId, maxId);

        criteriaQuery.where(idPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
