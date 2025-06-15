package com.scm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User 
{
    @Id
    private String userID;
    @Column(nullable = false, name = "user_name")
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String about;
    private String ProfilePic;
    private String phoneNumber;

    private boolean enabled=false;
    private boolean phoneVerified=false;
    private boolean emailVerified=false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)

    //one to many contacts linkage
    private List<Contact> contacts = new ArrayList<>();


     

}
