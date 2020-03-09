package net.ckj46.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_PROJECT")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "Employees_in_projects",
            joinColumns = {@JoinColumn(name = "Project")},
            inverseJoinColumns = {@JoinColumn(name = "Employee")}
    )
    List<Employee> employees = new LinkedList<>();

    public Project(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} ";
    }
}
