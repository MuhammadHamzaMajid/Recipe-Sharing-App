/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharingapp.demo.model.Users;
import recipesharingapp.demo.repository.UsersRepository;

/**
 *
 * @author HP
 */
@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepository repo;

    @PersistenceContext
    private EntityManager em;

    public Users getUser(Long id) {
        return repo.findById(id).get();
    }

    public void addUser(Users user) {
        repo.save(user);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

 public void changePassword(Long id, String newPassword) {
    // Validate the input parameters
    if (newPassword == null || newPassword.isBlank()) {
        throw new IllegalArgumentException("New password cannot be empty.");
    }

    // Find the user
    Users user = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

  
    try {
        // Update the password via a stored procedure
        StoredProcedureQuery spq = em.createStoredProcedureQuery("changePassword");

        spq.registerStoredProcedureParameter("newpasswordIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("useridIN", Long.class, ParameterMode.IN);

        spq.setParameter("newpasswordIN", newPassword);
        spq.setParameter("useridIN", id);

        spq.execute();
    } catch (Exception e) {
        throw new RuntimeException("Error while changing password: " + e.getMessage(), e);
    }
}

    
    public void updateUsername(String newUsername, Long id) {
        repo.updateUsernameById(newUsername, id);
    }
    
    public List<Users> findUsersByPartialUsername(String partialUsername) {
        if (partialUsername == null || partialUsername.isBlank()) {
            throw new IllegalArgumentException("Username must not be empty.");
        }

        return repo.findUsersByPartialUsername(partialUsername);
    }

}
