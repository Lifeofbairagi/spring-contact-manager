package com.scm.entities;

import java.util.ArrayList;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Contact
{
    @Id 
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone_number;
    private boolean isFavorite=false;
    private String picture;
    private String description;
    private String websiteLink;

    @ManyToOne
    private User user;

    //social links like insta facebook 
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
 
    private List<SocialLink> links = new ArrayList<>();



}
