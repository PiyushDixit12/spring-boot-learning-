package com.datamodeling.DataModeling.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * ONE-TO-ONE RELATIONSHIP
 * */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

//    cascade is used to auto save, fetch, delete if its relation is saved and deleted
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

}
