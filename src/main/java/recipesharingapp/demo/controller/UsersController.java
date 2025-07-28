package recipesharingapp.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipesharingapp.demo.model.Users;
import recipesharingapp.demo.service.UsersService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Users user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid user data. Username and password are required.");
        }
        service.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        Users user = service.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found.");
        }
        service.changePassword(id, newPassword);
        return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully.");
    }

    @PutMapping("/{id}/update-username")
    public ResponseEntity<String> updateUsername(@PathVariable Long id, @RequestBody String newUsername) {
        Users user = service.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found.");
        }
        service.updateUsername(newUsername, id);
        return ResponseEntity.status(HttpStatus.OK).body("Username updated successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Users user = service.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found.");
        }
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam String username) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username query parameter cannot be empty.");
        }

        List<Users> users = service.findUsersByPartialUsername(username);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No users found matching the given username.");
        }

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Users user = service.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found.");
        }
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }
}
