package com.datamodeling.DataModeling.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * ONE-TO-ONE RELATIONSHIP
 * <p>
 * This class represents a User entity in a one-to-one relationship with the Address entity.
 * <p>
 * Key Concepts:
 * <p>
 * - @Entity: Indicates that this class is a JPA entity and will be mapped to a database table.
 * - @Id: Specifies the primary key of the entity. This is the unique identifier for each record in the table.
 * - @GeneratedValue: Defines the strategy for generating primary key values. 'GenerationType.AUTO' allows the persistence provider to choose the best strategy (e.g., auto-increment).
 * - @OneToOne: Specifies a one-to-one relationship between User and Address. Each User has one Address, and each Address is associated with one User.
 * - @Cascade: Used to define the cascade operations that should be performed. 'CascadeType.ALL' means that all operations (persist, merge, remove, etc.) should be cascaded from User to Address.
 * - @JoinColumn: Specifies the column used for joining the two tables. 'name' defines the column in the User table that holds the foreign key, and 'referencedColumnName' specifies the column in the Address table that this foreign key references.
 * <p>
 * Advantages:
 * - Provides clear navigation in both directions: From User to Address and from Address to User.
 * - Ensures data consistency and easier maintenance by keeping both sides of the relationship synchronized.
 * <p>
 * Disadvantages:
 * - Increased complexity: Requires careful management of both sides of the relationship to avoid inconsistencies.
 * - Risk of circular references: If not managed properly, bidirectional relationships can lead to infinite loops in certain operations (e.g., serialization).
 *
 * @Data
 * @Entity public class User {
 * @Id
 * @GeneratedValue(strategy = GenerationType.AUTO)
 * private int id;
 * <p>
 * private String name;
 * /**
 * The Address associated with this User.
 * <p>
 * This relationship is mapped by the 'address_id' column in the User table,
 * which references the 'id' column in the Address table.
 * <p>
 * The cascade setting ensures that operations on the User entity (e.g., saving, deleting)
 * will also be performed on the associated Address entity.
 * <p>
 * IF YOU ARE USING THE BI-DIRECTION MAPPING
 * Advantages:
 * - Provides clear navigation in both directions: From User to Address and from Address to User.
 * - Ensures data consistency and easier maintenance by keeping both sides of the relationship synchronized.
 * <p>
 * Disadvantages:
 * - Increased complexity: Requires careful management of both sides of the relationship to avoid inconsistencies.
 * - Risk of circular references: If not managed properly, bidirectional relationships can lead to infinite loops in certain operations (e.g., serialization).
 * <p>
 * IF YOU ARE USING THE BI-DIRECTION MAPPING
 * Advantages:
 * - Provides clear navigation in both directions: From User to Address and from Address to User.
 * - Ensures data consistency and easier maintenance by keeping both sides of the relationship synchronized.
 * <p>
 * Disadvantages:
 * - Increased complexity: Requires careful management of both sides of the relationship to avoid inconsistencies.
 * - Risk of circular references: If not managed properly, bidirectional relationships can lead to infinite loops in certain operations (e.g., serialization).
 * <p>
 * IF YOU ARE USING THE BI-DIRECTION MAPPING
 * Advantages:
 * - Provides clear navigation in both directions: From User to Address and from Address to User.
 * - Ensures data consistency and easier maintenance by keeping both sides of the relationship synchronized.
 * <p>
 * Disadvantages:
 * - Increased complexity: Requires careful management of both sides of the relationship to avoid inconsistencies.
 * - Risk of circular references: If not managed properly, bidirectional relationships can lead to infinite loops in certain operations (e.g., serialization).
 */

/**
 * IF YOU ARE USING THE BI-DIRECTION MAPPING
 * Advantages:
 * - Provides clear navigation in both directions: From User to Address and from Address to User.
 * - Ensures data consistency and easier maintenance by keeping both sides of the relationship synchronized.
 * <p>
 * Disadvantages:
 * - Increased complexity: Requires careful management of both sides of the relationship to avoid inconsistencies.
 * - Risk of circular references: If not managed properly, bidirectional relationships can lead to infinite loops in certain operations (e.g., serialization).
 */

/**
 * IF YOU ARE USING  FETCH=FETCHTYPE.EAGER OR LAZY
 *
 *  - fetch = FetchType.LAZY: Indicates that the Address should be loaded lazily, i.e., only when accessed.
 * - fetch = FetchType.EAGER: Indicates that the Address should be loaded with user data in same time, i.e., means not execute query when you want to access it fetch it when user is fetched.
 *
 * - mappedBy: Indicates that the User entity is not the owner of the relationship. The 'address' field in the Address entity owns the relationship.
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    /**
     * The Address associated with this User.
     * <p>
     * This relationship is mapped by the 'address_id' column in the User table,
     * which references the 'id' column in the Address table.
     * <p>
     * The cascade setting ensures that operations on the User entity (e.g., saving, deleting)
     * will also be performed on the associated Address entity.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    /**
     * The Address associated with this User.
     *
     * This relationship is mapped by the 'address' field in the Address entity.
     */
    // UN-COMMENT IF YOU WANT TO ADD BI-DIRECTIONAL MAPPINGS
// @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
// private Address address;
}
