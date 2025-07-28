/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipesharingapp.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import recipesharingapp.demo.model.Users;

/**
 *
 * @author HP
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Modifying
    @Query(value = "UPDATE Users u SET u.username = :username WHERE u.id = :id")
    void updateUsernameById(@Param("username") String username, @Param("id") Long id);
    
    @Query("SELECT u FROM Users u WHERE u.username LIKE %:username%")
    List<Users> findUsersByPartialUsername(@Param("username") String username);
}
