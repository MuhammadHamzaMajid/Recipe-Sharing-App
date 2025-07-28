/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharingapp.demo.model.Ingredients;
import recipesharingapp.demo.model.RecipeIngredients;
import recipesharingapp.demo.repository.IngredientsRepository;
import recipesharingapp.demo.repository.RecipeIngredientsRepository;

/**
 *
 * @author HP
 */
@Service
public class RecipeIngredientsService {

    @Autowired
    private RecipeIngredientsRepository repo;

    public Optional<RecipeIngredients> getRecipeIngredients(Long id) {
        return repo.findById(id);

    }

    public void addRecipeIngredient(RecipeIngredients ingredient) {
        repo.save(ingredient);
    }

    public void deleteRecipeIngredient(Long id) {
        repo.deleteById(id);
    }

}
