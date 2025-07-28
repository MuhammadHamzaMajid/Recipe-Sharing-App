/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipesharingapp.demo.model.Recipes;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author HP
 */
public interface RecipesRepository extends JpaRepository<Recipes, Long> {

    @Query("SELECT r FROM Recipes r WHERE r.title = :title")
    List<Recipes> findByTitle(@Param("title") String title);

}
