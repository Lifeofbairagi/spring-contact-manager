package com.scm.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;



@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //user dashboard 
    //here in Requestmapping method=RequestMethod.GET was there but we can omit writing this it will assume Get method by default
    //but if you want Post method here you have to write it explicitly
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    
    //user profile page 
    @RequestMapping(value="/profile")
    public String userProfile(Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in : {}", username);

        //we can fetch data from databse as the user loads with the help of Helper class 
        return "user/profile"; 
    }
    

    //user add contact page



    //user delete contact page
    
    //user editcontact page


    //user view contact page 



}
