package com.scm.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {
    // This class is used to add common attributes to the model for all controllers
    @Autowired
    private UserService userService;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    public void addLoggedInUserInfo(Model model, Authentication authentication)
    {
        if(authentication == null)
        {
            return;
        }
        System.out.println("Adding logged in user info to the model");
        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in : {}", username);

         User user = userService.getUserByEmail(username);
         
            System.out.println(user);     
            System.out.println(user.getName());
            System.out.println(user.getEmail());

            model.addAttribute("loggedInUser", user);
         
         
    }
    
}
