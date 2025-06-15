package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller 
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home()
    {  
        System.out.println("Home Page");
        return "home";

    }

    @RequestMapping("/about")

    public String aboutpage()
    {
        System.out.println("About page Loading");
        return "about";
    }
    
    @RequestMapping("/services")

    public String servicespage()
    {
        System.out.println("Services page Loading");
        return "services";
    }
    
    //contact page 
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    

    //login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    //signup page
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        
        return new String("register");
    }
    
    //processing register 
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)

    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session)
    {
        System.out.println("processing regsiter");
        //we have to fetch form data
        //validate forn data
        System.out.println(userForm);
        //save to database
        //User user = User.builder()
        //.name(userForm.getName())
        //.email(userForm.getEmail())
        //.password(userForm.getPassword())
        //.about(userForm.getAbout())
        //.phoneNumber(userForm.getPhoneNumber())
        //.ProfilePic("https://s.gravatar.com/avatar/06e38ada6ce294394c47a150a39c81b8?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2F20.png")
        
        //.build();
        User user  = new User();
        user.setEmail(userForm.getEmail());
         user.setName(userForm.getName());
         user.setAbout(userForm.getAbout());
         user.setPassword(userForm.getPassword());
         user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://s.gravatar.com/avatar/06e38ada6ce294394c47a150a39c81b8?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2F20.png");
        

        User savedUser = userService.saveUser(user);
        System.out.println("user saved successfully");
        //return message saying reg successfull
        Message message = Message.builder().content("Registration Successfull").type(MessageType.red).build(); 
        session.setAttribute("message", message );

        //redirect to logged in page

         
        return "redirect:/register";
    }

    
}
