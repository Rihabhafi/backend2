package com.PFE.Backend.service;

import com.PFE.Backend.models.User;
import com.PFE.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Méthode pour ajouter un utilisateur
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Méthode pour mettre à jour un utilisateur
    public User updateUser(int id, User userDetails) {
        // Vérifier si l'utilisateur existe
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Mise à jour des champs de l'utilisateur
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setUsername(userDetails.getUsername());
            user.setRole(userDetails.getRole());

            // Sauvegarder l'utilisateur mis à jour
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }

    // Méthode pour récupérer un utilisateur par ID
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    

}
