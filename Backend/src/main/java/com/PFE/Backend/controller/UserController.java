package com.PFE.Backend.controller;

import com.PFE.Backend.DTO.UserMaxDTO;
import com.PFE.Backend.models.User;
import com.PFE.Backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3002/")
@RestController
@RequestMapping("/users")

public class UserController {
   
private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 1. Ajouter un utilisateur
    @PostMapping("/add")
    public UserMaxDTO createUser(@RequestBody UserMaxDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // 🔹 2. Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public UserMaxDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // 🔹 3. Récupérer tous les utilisateurs
    @GetMapping
    public List<UserMaxDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔹 4. Modifier un utilisateur
    @PutMapping("/{id}")
    public UserMaxDTO updateUser(@PathVariable Integer id, @RequestBody UserMaxDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    // 🔹 5. Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}



