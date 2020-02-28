package net.ckj46.domain;

import javax.persistence.*;

@Entity
@Table(name = "Cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CatSeq")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "cat")
    private CatOwner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatOwner getOwner() {
        return owner;
    }

    public void setOwner(CatOwner owner) {
        this.owner = owner;
    }

}
