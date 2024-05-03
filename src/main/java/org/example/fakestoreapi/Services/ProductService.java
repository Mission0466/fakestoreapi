package org.example.fakestoreapi.Services;

import org.example.fakestoreapi.Models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();

    Product createProduct(String title,
                  String description,
                  String category,
                  double price,
                  String image);


    void deleteProduct(Long productId);

    Product updateProduct(Long productId, String title, double price, String description, String image, String category);

    List<Product> getProductByCategory(String category);
}
