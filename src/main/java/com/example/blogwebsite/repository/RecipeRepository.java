package com.example.blogwebsite.repository;


import com.example.blogwebsite.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
