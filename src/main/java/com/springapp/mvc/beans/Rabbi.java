package com.springapp.mvc.beans;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;

@Entity(name = "rabbi")
@Table(name="rabbi")
public class Rabbi {
    @GeneratedValue
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="NUM", unique = true)
    private Integer num;
    @Column(name="NAME", unique=true)
    private String name;
    @Column(name="NICKNAME")
    private String nickname;
    @Column(name="BORN")
    private String born;
    @Column(name="DIED")
    private String died;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "rabbi"/*, orphanRemoval=true*/)
    private List<Book> books;
    @ManyToMany()
    @JoinTable(name="STUDENT")
    private List<Rabbi> students;
    @ManyToMany()
    @JoinTable(name="TEACHER")
    private List<Rabbi> teachers;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Rabbi{").append("id=").append(id).append(", name='").append(name).append('\'');
        sb.append(", books=").append(books);
//        sb.append(", students=");
//        for (Rabbi r: students){
//            sb.append(r.getName()).append(", ");
//        }
//        sb.append('}');
        return sb.toString();
    }

    public List<Rabbi> getStudents() {
        return students;
    }

    public void setStudents(List<Rabbi> students) {
        this.students = students;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getDied() {
        return died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public List<Rabbi> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Rabbi> teachers) {
        this.teachers = teachers;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
