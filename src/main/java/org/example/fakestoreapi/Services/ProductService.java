package org.example.fakestoreapi.Services;

import org.example.fakestoreapi.Models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);
}
