package com.employee.dimata.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    private String password;  // akan di-hash pakai Argon2
}
