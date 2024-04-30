package org.example.fakestoreapi.DTOS;

import lombok.Getter;
import lombok.Setter;
import org.example.fakestoreapi.Models.Category;
import org.example.fakestoreapi.Models.Product;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String image;

    private String description;

    private  String category;
    private double price;

    // the above attributes are from Fakestore api (JSON)
    public Product toProduct(){
        Product product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);

        Category cat = new Category();
        cat.setTitle(category);  // set the title of category

        product.setCategory(cat);  //assign the cat in product table

        return product;

    }
}
