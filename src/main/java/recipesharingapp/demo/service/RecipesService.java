/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipesharingapp.demo.model.Recipes;
import recipesharingapp.demo.repository.RecipesRepository;

/**
 *
 * @author HP
 */

@Service
@Transactional
public class RecipesService {

    @Autowired
    private RecipesRepository repo;

    public Recipes getRecipe(Long id) {
        return repo.findById(id).get();
    }

    public void addRecipe(Recipes recipe) {
        repo.save(recipe);
    }

    public void deleteRecipe(Long id) {
        repo.deleteById(id);
    }
    
    public List<Recipes> getRecipeByTitle(String title) {
        return repo.findByTitle(title);
    }

}
