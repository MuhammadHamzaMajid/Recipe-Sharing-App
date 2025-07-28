/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipesharingapp.demo.model.Ingredients;

/**
 *
 * @author HP
 */
public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{
    
    
    
    
}
