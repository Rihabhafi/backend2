package com.PFE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PFE.Backend.models.User;
import java.util.*;


public interface UserRepository extends JpaRepository<User,Integer> {

    Optional <User> findByUsername(String username);

}
