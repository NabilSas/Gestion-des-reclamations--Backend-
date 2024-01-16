package com.pfa.springjwt.controllers;

import com.pfa.springjwt.models.User;
import com.pfa.springjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/save-user")
    public <S extends User> S save(@RequestBody S entity) {
        return userService.save(entity);
    }

    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }



    @DeleteMapping("/deleteUser/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    /*@PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.findById(id);

        if (existingUser.isPresent()) {
            User currentUser = existingUser.get();
            currentUser.setNom(updatedUser.getNom());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setPassword(updatedUser.getPassword());
            // Add other fields to update as needed

            userService.save(currentUser); // Save the updated user
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
   /* @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.findById(id);

        if (existingUser.isPresent()) {
            User currentUser = existingUser.get();
            currentUser.setNom(updatedUser.getNom());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setPassword(updatedUser.getPassword());
            // Add other fields to update as needed

            userService.save(currentUser); // Save the updated user
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User updatedUserResult = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(updatedUserResult);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


   
}
