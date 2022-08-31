package ru.chernyshev.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "Select u from User u"),
        @NamedQuery(name = "countAllUsers", query = "Select count (u) from User u"),
        @NamedQuery(name = "deleteByUserId", query = "delete from User u where u.id = :id")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    private Customer customer;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    private List<Contact> contacts;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    @Column(nullable = false, length = 1024)
    private String password;

    @Embedded
    private Passport passport;

    public User(String username, List<Contact> contacts, String password) {
        this.username = username;
        this.contacts = contacts;
        this.password = password;
    }


}
