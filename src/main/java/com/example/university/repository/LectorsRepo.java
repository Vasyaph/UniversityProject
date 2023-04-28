package com.example.university.repository;

import com.example.university.entity.Lectors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorsRepo extends JpaRepository<Lectors, Long> {
    List<Lectors> findByFirstNameContainingIgnoreCase(String keyword);

    List<Lectors> findByLastNameContainingIgnoreCase(String keyword);
}
