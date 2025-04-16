package com.PFE.Backend.models;

public class AuthenticationResponse {

    private String token;
    private String role;  // Ajout du rôle
    private Integer userId; 
    private String username; 

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    // Constructeur
   

    // Getters et Setters
    public String getToken() {
        return token;
    }

   

    public AuthenticationResponse(String token, String role, Integer userId, String username) {
        this.token = token;
        this.role = role;
        this.userId = userId;
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
