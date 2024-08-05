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

import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    EntityManager entityManager;

 public    List<Product> findProductsByNameAndCategory(String productName ,String productCategory) {

//        create object of Criteria Builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

//        create a CriteriaQuery from criteria builder object
        CriteriaQuery<Product>  cq = cb.createQuery(Product.class);


        Root<Product> productRoot = cq.from(Product.class);

        Predicate productNamePredicate = cb.like(productRoot.get("name"),"%"+productName+"%");

        Predicate productCategoryPredicate = cb.equal(productRoot.get("category"),productCategory);

        cq.where(productNamePredicate,productCategoryPredicate);

        TypedQuery<Product> query = entityManager.createQuery(cq);

        return query.getResultList();

    }
}
