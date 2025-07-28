/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "recipes")
@NamedQueries({
    @NamedQuery(name = "Recipes.findAll", query = "SELECT r FROM Recipes r"),
    @NamedQuery(name = "Recipes.findByRecipeId", query = "SELECT r FROM Recipes r WHERE r.recipeId = :recipeId"),
    @NamedQuery(name = "Recipes.findByTitle", query = "SELECT r FROM Recipes r WHERE r.title = :title"),
    @NamedQuery(name = "Recipes.findByDescription", query = "SELECT r FROM Recipes r WHERE r.description = :description"),
    @NamedQuery(name = "Recipes.findByCreatedAt", query = "SELECT r FROM Recipes r WHERE r.createdAt = :createdAt")})
public class Recipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recipe_id")
    private Integer recipeId;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "created_at")
    private int createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Users userId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private Collection<RecipeIngredients> recipeIngredientsCollection;

    public Recipes() {
    }

    public Recipes(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Recipes(Integer recipeId, String title, int createdAt) {
        this.recipeId = recipeId;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Collection<RecipeIngredients> getRecipeIngredientsCollection() {
        return recipeIngredientsCollection;
    }

    public void setRecipeIngredientsCollection(Collection<RecipeIngredients> recipeIngredientsCollection) {
        this.recipeIngredientsCollection = recipeIngredientsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeId != null ? recipeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.recipeId == null && other.recipeId != null) || (this.recipeId != null && !this.recipeId.equals(other.recipeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "recipesharingapp.demo.model.Recipes[ recipeId=" + recipeId + " ]";
    }
    
}
