package org.example.fakestoreapi.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private Long id;

    private String title;
    private double price;

    private String description;
    private String imageURL;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

}
