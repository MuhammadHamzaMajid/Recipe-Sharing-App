/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "recipe_ingredients")
@NamedQueries({
    @NamedQuery(name = "RecipeIngredients.findAll", query = "SELECT r FROM RecipeIngredients r"),
    @NamedQuery(name = "RecipeIngredients.findById", query = "SELECT r FROM RecipeIngredients r WHERE r.id = :id"),
    @NamedQuery(name = "RecipeIngredients.findByQuantity", query = "SELECT r FROM RecipeIngredients r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "RecipeIngredients.findByUnit", query = "SELECT r FROM RecipeIngredients r WHERE r.unit = :unit")})
public class RecipeIngredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unit")
    private String unit;
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Recipes recipeId;
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Ingredients ingredientId;

    public RecipeIngredients() {
    }

    public RecipeIngredients(Integer id) {
        this.id = id;
    }

    public RecipeIngredients(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonProperty("recipes")
    public Recipes getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Recipes recipeId) {
        this.recipeId = recipeId;
    }

    @JsonProperty("ingredients")
    public Ingredients getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Ingredients ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeIngredients)) {
            return false;
        }
        RecipeIngredients other = (RecipeIngredients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "recipesharingapp.demo.model.RecipeIngredients[ id=" + id + " ]";
    }
    
}
