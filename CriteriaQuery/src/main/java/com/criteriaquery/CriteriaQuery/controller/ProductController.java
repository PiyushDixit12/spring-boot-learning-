package com.criteriaquery.CriteriaQuery.controller;

import com.criteriaquery.CriteriaQuery.entity.Product;
import com.criteriaquery.CriteriaQuery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(product, id);
    }

    @GetMapping
    List<Product> findProductByNameAndCategory(@RequestParam(required = false) String productName,
                                               @RequestParam(required = false) String productCategory) {
        return productService.findByNameAndCategory(productName, productCategory);
    }

    @GetMapping("/products")
    public List<Product> filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return productService.getFilteredProducts(name, category, minPrice, maxPrice);
    }


    @GetMapping(value = "/products/range")
    public  List<Product> getProductsInRangeById(@RequestParam Integer minId, @RequestParam Integer maxId) {
        return productService.getProductsInRangeById(minId, maxId);
    }


}
