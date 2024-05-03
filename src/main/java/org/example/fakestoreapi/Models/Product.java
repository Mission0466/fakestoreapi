package org.example.fakestoreapi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{

    private String title;
    private double price;

    private String description;
    private String imageURL;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

}
