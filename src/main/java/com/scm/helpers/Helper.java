package com.scm.helpers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication)
    {
        //if the user has ssigned in with email and password how the email will be retrived
        if(authentication instanceof OAuth2AuthenticationToken)
        {
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId(); 

            if(clientId.equalsIgnoreCase("google"))
            {
                System.out.println("Getting email from google");
            }
            else if(clientId.equalsIgnoreCase("github"))
            {
                System.out.println("Getting email from github");
            }

            return "";
        }

        else
        {
            System.out.println("getting email from local databse");
            return authentication.getName();
        }
    }
    
}
