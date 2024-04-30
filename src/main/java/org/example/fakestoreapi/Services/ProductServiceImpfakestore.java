package org.example.fakestoreapi.Services;

import org.example.fakestoreapi.DTOS.FakeStoreProductDTO;
import org.example.fakestoreapi.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("productServiceImpfakestore")
public class ProductServiceImpfakestore implements ProductService{


   RestTemplate restTemplate;

   //constructor
    @Autowired
    public ProductServiceImpfakestore(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getSingleProduct(Long productId) {
        // 1.first we need call the URL of fakestore through rest template
        // 2. return the product so that we can see in postman or google chrome.

        FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.getForObject("Https://fakestoreapi.com/products/" + productId,  FakeStoreProductDTO.class);  //getting the response from fakestore URL(which in JSON) and saving those in the FakestoreDTO class to convert to java pojo
        return fakeStoreProductDTO.toProduct();
    }
}
