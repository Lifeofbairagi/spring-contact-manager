package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard 
    //here in Requestmapping method=RequestMethod.GET was there but we can omit writing this it will assume Get method by default
    //but if you want Post method here you have to write it explicitly
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    

    //user add contact page

    //user delete contact page
    
    //user editcontact page

    //user profile page 

    //user view contact page 



}
