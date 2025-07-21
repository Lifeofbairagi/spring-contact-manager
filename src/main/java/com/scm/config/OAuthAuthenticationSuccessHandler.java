package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger=LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;
    

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        

                logger.info("OAuthAthenticationSuccessHandler");

                //first identufy which provider is ther ethen take its attributes 
                 var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

                 String authorisedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

                 logger.info(authorisedClientRegistrationId);

                 var oauthUser = (DefaultOAuth2User)authentication.getPrincipal();

                 oauthUser.getAttributes().forEach((key,value)->{
                    logger.info(key + ":" + value);
                 });

                 User user = new User();
                 user.setUserID(UUID.randomUUID().toString());
                 user.setRoleList(List.of(AppConstants.ROLE_USER));
                 user.setEmailVerified(true);
                 user.setEnabled(true);


                 if(authorisedClientRegistrationId.equalsIgnoreCase("google"))
                 {
                    //google code 
                    user.setEmail(oauthUser.getAttribute("email").toString());
                    user.setProfilePic(oauthUser.getAttribute("picture").toString());
                    user.setName(oauthUser.getAttribute("name").toString());
                    user.setProviderId(oauthUser.getName());
                    user.setProvider(Providers.GOOGLE);
                    user.setPassword("password");
                    user.setAbout("this account is created using google");

                 }
                
                 else if(authorisedClientRegistrationId.equalsIgnoreCase("github"))
                 {
                    //github code 
                    String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString() + "@gmail.com";
                    String picture = oauthUser.getAttribute("avatar_url").toString();
                    String name = oauthUser.getAttribute("login").toString();
                    String providerId = oauthUser.getName();

                    user.setEmail(email);
                    user.setProfilePic(picture);
                    user.setName(name);
                    user.setProviderId(providerId);
                    user.setProvider(Providers.GITHUB);
                    user.setPassword("password");
                    user.setAbout("this account is createde using github");

                 }
                 
                 else
                 {
                    logger.error("Unknown OAuth2 provider: {}", authorisedClientRegistrationId);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown OAuth2 provider");
                    return;
                 }

                





                //DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();

                /*logger.info(user.getName());
                

                 user.getAttributes().forEach((key,value)->{
                    logger.info("{} => {}",key, value);

                });

                logger.info(user.getAuthorities().toString());

                before redirecting we save data to database
                String email=user.getAttribute("email").toString();
                String name = user.getAttribute("name").toString();
                String picture = user.getAttribute("picture").toString();

                //create user and save in database
                User user1 = new User();
                user1.setEmail(email);
                user1.setEmailVerified(true);
                user1.setProfilePic(picture);
                user1.setName(name);
                user1.setEnabled(true);
                user1.setPassword("password");
                user1.setProvider(Providers.GOOGLE);
                user1.setUserID(UUID.randomUUID().toString());
                user1.setProviderId(user.getName());
                user1.setRoleList(List.of(AppConstants.ROLE_USER));
                user1.setAbout("This account is created using google");


                //check if the yser already exists if not then save the user in database 

                User user2 = userRepo.findByEmail(email).orElse(null);
                if(user2==null)
                {
                    userRepo.save(user1);
                    logger.info("user saved successfully..."+ email);
                }
                
                 */

                 User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
                if(user2==null)
                {
                    userRepo.save(user);
                    logger.info("user saved successfully..."+ user.getEmail());
                }

                response.sendRedirect("/user/profile");

        
    }
 

}
