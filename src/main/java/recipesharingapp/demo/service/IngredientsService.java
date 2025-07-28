package recipesharingapp.demo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharingapp.demo.exception.ResourceNotFoundException;
import recipesharingapp.demo.model.Ingredients;
import recipesharingapp.demo.repository.IngredientsRepository;



/**
 * Service layer for managing Ingredients.
 */
@Service
@Transactional
public class IngredientsService {

    @Autowired
    private IngredientsRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

  
    public Ingredients getIngredient(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with ID: " + id));
    }

 
    public void addIngredient(Ingredients ingredient) {
       
        try {
            StoredProcedureQuery spq = entityManager.createStoredProcedureQuery("addIngredient");

            // Register procedure parameters
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("typeIN", String.class, ParameterMode.IN);

            // Set procedure parameter values
            spq.setParameter("nameIN", ingredient.getName());
            spq.setParameter("typeIN", ingredient.getType());

            // Execute the stored procedure
            spq.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error while adding ingredient: " + e.getMessage(), e);
        }
    }

 
    public void deleteIngredient(Long id) throws Exception {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Ingredient not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}
