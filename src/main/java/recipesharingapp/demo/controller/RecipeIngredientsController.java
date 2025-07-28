package recipesharingapp.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipesharingapp.demo.exception.ResourceNotFoundException;
import recipesharingapp.demo.model.RecipeIngredients;
import recipesharingapp.demo.service.RecipeIngredientsService;

/**
 * REST Controller for managing Recipe Ingredients.
 */
@RestController
@RequestMapping("/api/recipe-ingredients")
public class RecipeIngredientsController {

    @Autowired
    private RecipeIngredientsService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeIngredientById(@PathVariable("id") Long id) {
        try {
            Optional<RecipeIngredients> recipeIngredientOpt = service.getRecipeIngredients(id);

            if (recipeIngredientOpt.isPresent()) {
                return ResponseEntity.ok(recipeIngredientOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Recipe-ingredient with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the ingredient: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addRecipeIngredient(@RequestBody RecipeIngredients recipeIngredient) {
        if (recipeIngredient == null || recipeIngredient.getRecipeId() == null || recipeIngredient.getIngredientId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid recipe-ingredient data.");
        }

        service.addRecipeIngredient(recipeIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Recipe-ingredients created succesfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipeIngredient(@PathVariable("id") Long id) {
        try {
            service.deleteRecipeIngredient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Recipe-ingredient deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recipe-ingredient with ID " + id + " not found.");
        }
    }

}
