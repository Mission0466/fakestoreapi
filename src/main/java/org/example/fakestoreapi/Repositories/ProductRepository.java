package org.example.fakestoreapi.Repositories;

import jakarta.transaction.Transactional;
import org.example.fakestoreapi.Models.Product;
import org.example.fakestoreapi.Repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);


    List<Product> findAll();

    Product findByIdIs(Long id);

    @Transactional
    void deleteProductById(Long id);

    List<Product> findByCategoryTitle(String title);


    // HQl queries
    @Query("select p from Product p where p.category.title = :title and p.id = :productId")   // product which we are using here is from models class not from sql table
    Product getTheProductWithParticularName(@Param("title") String title, @Param("productId") Long productId);


    // we are trying to get Id and title of products of given category
    @Query("select p.title as title, p.id as id from Product p where p.category.id= :categoryId")  // product which we are using here is from models class not from sql table
    List<ProductWithIdAndTitle> getTitleOfProductsOfGivenCategory(@Param("categoryId") Long categoryId);
}


