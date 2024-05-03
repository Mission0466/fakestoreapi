package org.example.fakestoreapi.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // after fakestore
    private Date createdAt;   // helps us to create a date when this table has created
    private Date lastUpdatedAt;  // helps us to update a date when this table has modified
    private boolean isDeleted;  // instead of drop the whole table we can use a boolean value to check it is in deleted state or not !!
    // after fakestore
}
