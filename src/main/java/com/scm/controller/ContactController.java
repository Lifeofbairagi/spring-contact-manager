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
@RequestMapping("/user/contacts")
public class ContactController 
{
    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add")
    public String addContactView(Model model, Authentication authentication)
    {
        String username = Helper.getEmailOfLoggedInUser(authentication);

                logger.info("User logged in : {}", username);

                 User user = userService.getUserByEmail(username);
                 System.out.println(user.getName());
                 System.out.println(user.getEmail());
                model.addAttribute("loggedInUser", user);

                return "user/add_contact";

    }
    
}
