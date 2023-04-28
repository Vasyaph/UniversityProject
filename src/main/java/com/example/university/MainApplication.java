package com.example.university;

import com.example.university.entity.Departments;
import com.example.university.entity.Lectors;
import com.example.university.repository.DepartmentsRepo;
import com.example.university.repository.LectorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MainApplication implements CommandLineRunner {
    private final DepartmentsRepo departmentsRepo;
    private final LectorsRepo lectorsRepo;

    @Autowired
    public MainApplication(DepartmentsRepo departmentsRepo, LectorsRepo lectorsRepo) {
        this.departmentsRepo = departmentsRepo;
        this.lectorsRepo = lectorsRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner myObj = new Scanner(System.in);


        //Create patterns for search correct answer
        String headPattern = "Who is head of department \\b(\\w+\\s?)\\b";//1
        String statisticPattern = "Show \\b(\\w+\\s?)\\b statistics";//2
        String salaryPattern = "Show the average salary for the department \\b(\\w+\\s?)\\b";//3
        String countEmployeePattern = "Show count of employee for \\b(\\w+\\s?)\\b";//4
        String searchPattern = "Global search by \\b(\\w+\\s?)\\b";//5


        System.out.println(lectorsRepo.findAll());//we can remove this. is output all entity in databases
        while (true) {
            System.out.println("input you`r question:");
            String userInput = myObj.nextLine();
            if (userInput.matches(headPattern)) {//1
                String keyword = getParameterFromLine(userInput, headPattern);

                Optional<Departments> departments = departmentsRepo.findFirstByName(keyword);
                departments
                        .ifPresentOrElse(a -> System.out.println("Head of " + keyword + " department is " + a.getHead())
                                ,()->System.out.println("not fount"));
            } else if (userInput.matches(statisticPattern)) {//2
                String keyword = getParameterFromLine(userInput, statisticPattern);
                System.out.println(
                        "assistans: "+departmentsRepo.countAssistantsByDepartmentName(keyword)+
                        "\nassociate professors: "+departmentsRepo.countAssociateProfessorsByDepartmentName(keyword)+
                        "\nprofessors:"+departmentsRepo.countProfessorsByDepartmentName(keyword));

            } else if (userInput.matches(salaryPattern)) {//3
                String keyword = getParameterFromLine(userInput, salaryPattern);
                System.out.println(departmentsRepo.getAverageSalaryByDepartment(keyword));

            } else if (userInput.matches(countEmployeePattern)) {//4
                String keyword = getParameterFromLine(userInput, countEmployeePattern);
                System.out.println("The average salary of "+keyword+" is "+departmentsRepo.countEmployeesByDepartmentName(keyword));

            } else if (userInput.matches(searchPattern)) {//5
                String keyword = getParameterFromLine(userInput, searchPattern);
                Set<Lectors> lectors = new HashSet<>();

                lectors.addAll(lectorsRepo.findByFirstNameContainingIgnoreCase(keyword));
                lectors.addAll(lectorsRepo.findByLastNameContainingIgnoreCase(keyword));
                lectors.forEach(a -> System.out.println(a.getFirstName()+" "+a.getLastName()));
            } else {
                System.out.println("wrong input. Try again");
            }
        }
    }

    private String getParameterFromLine(String inLine, String inPattern) {
        int indexWord = Arrays.asList(inPattern.split(" ")).indexOf("\\b(\\w+\\s?)\\b");
        return inLine.split(" ")[indexWord];
    }

}
