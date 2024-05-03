package org.example.fakestoreapi.Repositories;

import org.example.fakestoreapi.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);

    Category save(Category category);

    Category findByid(Long id);

}
