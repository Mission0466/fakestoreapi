package org.example.fakestoreapi.Services;

import org.apache.catalina.valves.JsonAccessLogValve;
import org.example.fakestoreapi.Models.Category;
import org.example.fakestoreapi.Models.Product;
import org.example.fakestoreapi.Repositories.CategoryRepository;
import org.example.fakestoreapi.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

   private ProductRepository productRepository;

   private CategoryRepository categoryRepository;

   //constructor
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);

        // first we need to search whether the given category is there in category table or not
        Category categoryFromDB = categoryRepository.findByTitle(category);

        // if the category is not present then we need to create a new category title in DB
        if(categoryFromDB == null){
            Category newC = new Category();
            newC.setTitle(category); // now set the title in category table of DB
            categoryFromDB = newC;
        }
        // set the category
        product.setCategory(categoryFromDB); // here we are setting the tile of category in product object

        // save the product
       productRepository.save(product);  // finally we are saving the product in the DB through the repo layer.

        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteProductById(productId);
    }

    @Override
    public Product updateProduct(Long productId, String title, double price, String description, String image, String category) {
        //step 1:
        // first search the id of the product if exists then do the update the product attributes
        Product p = productRepository.findByIdIs(productId);
        if(p != null){
            p.setTitle(title);
            p.setPrice(price);
            p.setDescription(description);
            p.setImageURL(image);

            Category cat = categoryRepository.findByTitle(category);
            p.setCategory(cat);

        }
        return productRepository.save(p);
    }

    @Override
    public List<Product> getProductByCategory(String title) {
        return productRepository.findByCategoryTitle(title);
    }
}
