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
public class Departments {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String head;

    @ManyToMany
    @JoinTable(
            name="departments_lectors",
            joinColumns = {@JoinColumn (name="department_id")},
            inverseJoinColumns = {@JoinColumn(name="lector  _id")})
    private Set<Lectors> lectors = new HashSet<Lectors>();

}
