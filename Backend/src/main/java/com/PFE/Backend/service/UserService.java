package com.PFE.Backend.service;

import com.PFE.Backend.DTO.UserMaxDTO;
import com.PFE.Backend.mapper.UserMaxMapper;
import com.PFE.Backend.models.EtatEquipement;
import com.PFE.Backend.models.Role;
import com.PFE.Backend.models.User;
import com.PFE.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMaxMapper userMaxMapper;

    public UserService(UserRepository userRepository, UserMaxMapper userMaxMapper) {
        this.userRepository = userRepository;
        this.userMaxMapper = userMaxMapper;
    }

    // 🔹 1. Créer un utilisateur
    public UserMaxDTO createUser(UserMaxDTO userDTO) {
        User user = userMaxMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMaxMapper.userToUserDTO(savedUser);
    }

    // 🔹 2. Obtenir un utilisateur par ID
    public UserMaxDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + id));
        return userMaxMapper.userToUserDTO(user);
    }

    // 🔹 3. Obtenir tous les utilisateurs
    public List<UserMaxDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMaxMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    // 🔹 4. Mettre à jour un utilisateur
    public UserMaxDTO updateUser(Integer id, UserMaxDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + id));

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setUsername(userDTO.getUsername());
        
        try {
            existingUser.setRole(Role.valueOf(userDTO.getRole().toUpperCase())); // Uppercase pour éviter les erreurs de casse
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Valeur invalide pour le role : " + userDTO.getRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return userMaxMapper.userToUserDTO(updatedUser);
    }

    // 🔹 5. Supprimer un utilisateur
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
