package net.ckj46.domain;

import javax.persistence.*;

@Entity
@Table(name="CatOwners")
public class CatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CatOwnerSeq")
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne
    private Cat cat;

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

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

}
