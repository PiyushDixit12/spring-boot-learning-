package com.criteriaquery.CriteriaQuery.service;

import com.criteriaquery.CriteriaQuery.dao.ProductDao;
import com.criteriaquery.CriteriaQuery.entity.Product;
import com.criteriaquery.CriteriaQuery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDao productDao;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, Long id) {
        Optional<Product> product1 = productRepository.findById(id);
        if (product1.isEmpty()) {
            return null;
        }
        return productRepository.save(product);
    }

    public List<Product> findByNameAndCategory(String name, String category) {
//        return productDao.findProductsByNameAndCategory(name, category);
        return  productRepository.findByNameContainingAndCategoryContaining(name,category);
    }
    public List<Product> getFilteredProducts(String name, String category, Double minPrice, Double maxPrice) {
        return productDao.filterProducts(name, category, minPrice, maxPrice);
    }

    public  List<Product> getProductsInRangeById(Integer minId , Integer maxId) {
        return productDao.getAllProductsOnRangeOfId(minId, maxId);
    }
}
