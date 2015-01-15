package com.springapp.rabbis.beans;

import com.springapp.rabbis.NamedBean;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.cache.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
//import javax.persistence.Cacheable;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity(name = "rabbi")
@Table(name="rabbi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "hiberante")
@org.springframework.cache.annotation.Cacheable
@CacheConfig(cacheNames = "searchResults")
public class Rabbi implements NamedBean{
    @GeneratedValue
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="NUM", unique = true)
    private Integer num;
    @NotEmpty
    @NotNull
    @Column(name="NAME", unique=true)
    private String name;
    @Column(name="NICKNAME")
    private String nickname;
    @Column(name="BORN")
    private String born;
    @Column(name="DIED")
    private String died;
    @Column(name="BORN_GEORGIAN")
    private String bornGeorgian;
    @Column(name="DIED_GEORGIAN")
    private String diedGeorgian;
    @Column(name="BIRTH_LOCATION")
    private String birthLocation;
    @Column(name="DEATH_LOCATION")
    private String deathLocation;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="RELATION")
    private String relation;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "rabbi"/*, orphanRemoval=true*/)
    private List<Book> books;
    @ManyToMany(/*cascade = CascadeType.ALL*/)
    @JoinTable(name="STUDENT")
    private List<Rabbi> students;
    @ManyToMany(/*cascade = CascadeType.ALL*/)
    @JoinTable(name="TEACHER")
    private List<Rabbi> teachers;

    public void setName(String name) {
        this.name = name;
    }
    @Override
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

        sb.append("Rabbi{").append("id=").append(id).append(", num=").append(num).append(", name='").append(name).append('\'');
        sb.append(", nickname=").append(nickname).append(", born=").append(born).append(", died=").append(died);
        sb.append(", bornGeorgian=").append(bornGeorgian).append(", diedGeorigan=").append(diedGeorgian);
        sb.append(", birthLocation=").append(birthLocation).append(", deathLocation=").append(deathLocation);
        sb.append(", books=").append(books);

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
    public String getBornGeorgian() {
        return bornGeorgian;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getDied() {
        return died;
    }

    public String getDiedGeorgian() { return diedGeorgian; }

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

    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public String getDeathLocation() {
        return deathLocation;
    }

    public void setDeathLocation(String deathLocation) {
        this.deathLocation = deathLocation;
    }

    public void setBornGeorgian(String bornGeorgian) {
        this.bornGeorgian = bornGeorgian;
    }

    public void setDiedGeorgian(String diedGeorgian) {
        this.diedGeorgian = diedGeorgian;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
