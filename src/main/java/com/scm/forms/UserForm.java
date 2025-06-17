package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserForm 
{
    @NotBlank
    @Size(min = 3, message ="Minimum 3 characters are required")
    private String name;

    @Email(message="invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message="password is required")
    @Size(min = 6, message="password must be atleast 6 characters long")
    private String password;

    @NotBlank
    @Size(max = 250)
    private String about;

    @NotBlank
    @Size(min = 10, max = 10,message="do not use country code")
    private String phoneNumber;
}
