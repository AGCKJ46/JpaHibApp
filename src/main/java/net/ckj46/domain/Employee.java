package net.ckj46.domain;

import javax.persistence.*;

@Entity
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="employeeId")
    private Long id;

    @Column(nullable = false, length=30)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(70) NOT NULL")
    private String lastName;

    @Column(nullable = false, precision = 5, scale = 0)
    private Long salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
