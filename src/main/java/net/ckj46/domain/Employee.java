package net.ckj46.domain;

import javax.persistence.*;

/*
    create trigger calculate_tax
    before insert on Employees for each row
    set new.tax=new.salary*0.2;
*/

@Entity
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length=30)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(70) NOT NULL")
    private String lastName;

    @Column(nullable = false, precision = 5, scale = 0)
    private Long salary;

    @OneToOne
    @JoinColumn(name = "addressId") // ta adnotacja dodaje w tej encji kolumne z identyfikatorem adrersu
    private Address address;

    private double tax;

    public Employee(String firstName, String lastName, Long salary, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.address = address;
    }

    public Employee(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}

    public double getTax() {
        return tax;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", address=" + address.toString() +
                ", tax=" + tax +
                '}';
    }
}
