package com.example.university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Lectors {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private int salary;
    private String lastName;
    @Enumerated(EnumType.ORDINAL)
    private Degree degree;
    @ManyToMany(mappedBy = "lectors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Departments> departments=new HashSet<Departments>();
}
