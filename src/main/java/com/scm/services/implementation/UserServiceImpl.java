package com.scm.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService 
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;

private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        //user id has to be generated
        String userId = UUID.randomUUID().toString();
        user.setUserID(userId);

        //password encoding here 
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        //similarly encoded password should be there


        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {

        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 =  userRepo.findById(user.getUserID()).orElseThrow(()-> new ResourceNotFoundException("Resouce not found"));

        //updating user2 from user
         user2.setName(user.getName());
         user2.setEmail(user.getEmail());
         user2.setAbout(user.getAbout());
         user2.setPassword(user.getPassword());
         user2.setPhoneNumber(user.getPhoneNumber());
         user2.setProfilePic(user.getProfilePic());
         user2.setEnabled(user.isEnabled());
         user2.setEmailVerified(user.isEmailVerified());
         user2.setPhoneVerified(user.isPhoneVerified());
         user2.setProvider(user.getProvider());
         user2.setProviderId(user.getProviderId());

         //now save the user in databse
         User save = userRepo.save(user2);

                  return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2 =  userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resouce not found"));
        userRepo.delete(user2);

    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       User user =  userRepo.findByEmail(email).orElse(null);
       return user != null ? true : false;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) 
    {
        return userRepo.findByEmail(email).orElse(null);
    } 


}
