package com.codefusion.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME" ,nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "USERNAME" ,nullable = false, columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(name = "PASSWORD" ,nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "ROLE" ,nullable = false, columnDefinition = "VARCHAR(20)")
    private String role;
}
