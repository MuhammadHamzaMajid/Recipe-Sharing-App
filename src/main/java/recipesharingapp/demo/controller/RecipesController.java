package recipesharingapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipesharingapp.demo.exception.ResourceNotFoundException;
import recipesharingapp.demo.model.Recipes;
import recipesharingapp.demo.service.RecipesService;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRecipeByID(@PathVariable Long id) {
        try {
            Recipes recipe = recipesService.getRecipe(id);
            if (recipe == null) {
                throw new ResourceNotFoundException("Recipe with ID " + id + " not found.");
            }
            return ResponseEntity.ok(recipe);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Recipes>> getRecipeByTitle(@PathVariable String title) {
        List<Recipes> recipes = recipesService.getRecipeByTitle(title);
        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(recipes);
        }
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRecipe(@RequestBody Recipes recipe) {
//        // Validate if the recipe object is null
//        if (recipe == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid recipe data. Recipe object cannot be null.");
//        }
//
//        // Validate the recipe's title
//        if (recipe.getTitle() == null || recipe.getTitle().trim().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid recipe data. Title is required.");
//        }
//
//        // Validate the recipe's ingredients
//        if (recipe.getRecipeIngredientsCollection() == null || recipe.getRecipeIngredientsCollection().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid recipe data. At least one ingredient is required.");
//        }

        // Save the recipe
        try {
            recipesService.addRecipe(recipe);
            return ResponseEntity.status(HttpStatus.CREATED).body("Recipe created successfully.");
        } catch (Exception e) {
            // Handle any unexpected exceptions during the save process
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the recipe: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        try {
            Recipes existingRecipe = recipesService.getRecipe(id);
            if (existingRecipe == null) {
                throw new ResourceNotFoundException("Recipe with ID " + id + " not found.");
            }
            recipesService.deleteRecipe(id);
            return ResponseEntity.status(HttpStatus.OK).body("Recipe deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
