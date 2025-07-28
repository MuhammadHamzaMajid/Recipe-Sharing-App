package recipesharingapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipesharingapp.demo.exception.ResourceNotFoundException;
import recipesharingapp.demo.model.Ingredients;
import recipesharingapp.demo.service.IngredientsService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable("id") Long id) {
        try {
            Ingredients ingredient = service.getIngredient(id);
            return ResponseEntity.ok(ingredient);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingredient with ID " + id + " not found.");
        }
    }

    @PostMapping
    public ResponseEntity<String> addIngredient(@RequestBody Ingredients ingredient) {
        if (ingredient == null || ingredient.getName() == null || ingredient.getType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid ingredients data");
        }

        service.addIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingredient created successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("id") Long id) throws Exception {
        try {
            service.deleteIngredient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Ingredient deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingredient with ID " + id + " not found.");
        }
    }
}
