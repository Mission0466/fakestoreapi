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

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
       FakeStoreProductDTO[]  fakeStoreProductDTOS = restTemplate.getForObject("Https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

       //for loop to fill our product list from fakeStroeProductDTO[]
        for(int i=0; i<fakeStoreProductDTOS.length; i++){
            productList.add(fakeStoreProductDTOS[i].toProduct());
        }
        return productList;
    }

    @Override
    public Product createProduct(String title , String description, String category, double price, String image) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setDescription(description);

        FakeStoreProductDTO response = restTemplate.postForObject("Https://fakestoreapi.com/products", fakeStoreProductDTO, FakeStoreProductDTO.class);

        return response.toProduct();


    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete("Https://fakestoreapi.com/products/" + productId);
    }

    @Override
    public Product updateProduct(Long productId, String title, double price, String description, String image, String category) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(productId);
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setDescription(description);

        FakeStoreProductDTO response = restTemplate.patchForObject("Https://fakestoreapi.com/products" + productId, fakeStoreProductDTO, FakeStoreProductDTO.class);

        return response.toProduct();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return null;
    }

}



// steps:
//1. first it will get the data  in JSON format
//2. we need to store the data in our DB. In order we cannot connect those directly to our model class (product) because the names were different in JSON and our model class.
//3. so we have created a new layer called DTO and mentioned the same names as per the JSON file. from here we are assigning our JSON data to our model class.
//4. after getting assigned to our model class we need to return the data to controller. jackson will take care of conversions.

//[
//
//        {
//
//        }
//        {
//
//        }
//]