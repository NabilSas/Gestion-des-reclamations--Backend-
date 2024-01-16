package com.pfa.springjwt.service;

import com.pfa.springjwt.models.Reclamation;
import com.pfa.springjwt.models.User;
import com.pfa.springjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private ReclamationService reclamationService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    public Optional<User> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Update fields of the existing user with the values from the updated user
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            // Add more fields to update as needed

            return userRepository.save(existingUser);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }


}
