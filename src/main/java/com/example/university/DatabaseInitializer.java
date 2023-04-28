package com.example.university;

import com.example.university.entity.Degree;
import com.example.university.entity.Departments;
import com.example.university.entity.Lectors;
import com.example.university.repository.DepartmentsRepo;
import com.example.university.repository.LectorsRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Transactional
public class DatabaseInitializer {
    private final DepartmentsRepo departmentsRepo;
    private final LectorsRepo lectorsRepo;
    @Autowired
    public DatabaseInitializer(DepartmentsRepo departmentsRepo,LectorsRepo lectorsRepo){
        this.departmentsRepo=departmentsRepo;
        this.lectorsRepo=lectorsRepo;
    }

    @PostConstruct
    public void initialize() {
        Lectors lector1=new Lectors();
        lector1.setFirstName("Vlad");
        lector1.setLastName("Yehorov");
        lector1.setSalary(15000);
        lector1.setDegree(Degree.ASSISTANT);

        Lectors lector2=new Lectors();
        lector2.setFirstName("Anton");
        lector2.setLastName("Ogurcov");
        lector2.setSalary(20000);
        lector2.setDegree(Degree.ASSOCIATE_PROFESSOR);

        Lectors lector3=new Lectors();
        lector3.setFirstName("Micha");
        lector3.setLastName("Gladun");
        lector3.setSalary(25000);
        lector3.setDegree(Degree.PROFESSOR);

        Lectors lector4=new Lectors();
        lector4.setFirstName("Alex");
        lector4.setLastName("Braun");
        lector4.setSalary(30000);
        lector4.setDegree(Degree.ASSOCIATE_PROFESSOR);


        Departments department1=new Departments();
        department1.setHead("Alexandr");
        department1.setName("FTIT");

        Departments department2=new Departments();
        department2.setHead("Timofei");
        department2.setName("FAAN");

        Departments department3=new Departments();
        department3.setHead("Daniil");
        department3.setName("FSNX");


        department1.setLectors(Set.of(lector1,lector3));
        department2.setLectors(Set.of(lector4,lector2,lector1));



        lectorsRepo.save(lector1);
        lectorsRepo.save(lector2);
        lectorsRepo.save(lector3);
        lectorsRepo.save(lector4);

        departmentsRepo.save(department1);
        departmentsRepo.save(department2);
        departmentsRepo.save(department3);

    }
}
