package net.ckj46.domain;

import javax.persistence.*;

@Entity
@Table(name="Employees")
// pkJoinColumns pozwala jak ma się nazywać kolumna z kluczem obcym z tab. Employees w tab. Addresses
// @SecondaryTable(name = "Addresses", pkJoinColumns = @PrimaryKeyJoinColumn(name = "employeeId"))
@SecondaryTable(name = "Addresses")
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

    @Column(table = "Addresses")
    private String locality;
    @Column(table = "Addresses")
    private String zipCode;
    @Column(table = "Addresses")
    private String street;
    @Column(table = "Addresses")
    private int streetNumber;

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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
}
