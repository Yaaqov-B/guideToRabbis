package com.springapp.mvc.beans;


import javax.persistence.*;

@Entity(name = "book")
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;
    @Column(name="NAME")
    private String name;
    @ManyToOne()
    @JoinColumn(name ="rabbi_id")
    private Rabbi rabbi;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rabbi=" + rabbi.getName() +
                '}';
    }

    public Rabbi getRabbi() {
        return rabbi;
    }

    public void setRabbi(Rabbi rabbi) {
        this.rabbi = rabbi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
