package com.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entities.User;
import java.util.List;
import java.util.*;


public interface UserRepo extends JpaRepository<User, String>
{
    //extra methods for db related operations
    //custom query methods
    //custon finder methods
    
    Optional<User> findByEmail(String email);
} 