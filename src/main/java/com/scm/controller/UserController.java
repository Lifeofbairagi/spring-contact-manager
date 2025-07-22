package com.scm.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;




@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute

    

    //user dashboard 
    //here in Requestmapping method=RequestMethod.GET was there but we can omit writing this it will assume Get method by default
    //but if you want Post method here you have to write it explicitly
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    
    //user profile page 
    @RequestMapping(value="/profile")
    public String userProfile(Model model,Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);

       

        logger.info("User logged in : {}", username);

         User user = userService.getUserByEmail(username);
        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);
        return "user/profile"; 
    }
    

    //user add contact page



    //user delete contact page
    
    //user editcontact page


    //user view contact page 



}
