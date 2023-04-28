package com.example.university.repository;


import com.example.university.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentsRepo extends JpaRepository<Departments, Long> {
    Optional<Departments> findFirstByName(String str);

    @Query("SELECT COUNT(l) FROM Lectors l JOIN l.departments d WHERE d.name = :departmentName AND l.degree = 0")
    int countAssistantsByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT COUNT(l) FROM Lectors l JOIN l.departments d WHERE d.name = :departmentName AND l.degree = 1")
    int countAssociateProfessorsByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT COUNT(l) FROM Lectors l JOIN l.departments d WHERE d.name = :departmentName AND l.degree = 2")
    int countProfessorsByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT AVG(l.salary) FROM Departments d JOIN d.lectors l WHERE d.name = :departmentName")
    Double getAverageSalaryByDepartment(@Param("departmentName") String departmentName);

    @Query("SELECT COUNT(e) FROM Lectors e JOIN e.departments d WHERE d.name = :departmentName")
    Long countEmployeesByDepartmentName(@Param("departmentName") String departmentName);

}
