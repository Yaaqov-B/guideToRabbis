package com.springapp.rabbis.beans;


import com.springapp.rabbis.NamedBean;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@org.springframework.cache.annotation.Cacheable

@Entity(name = "book")
@Table(name="book")
//@Cacheable
@CacheConfig(cacheNames = "searchResults")
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "searchResults")

public class Book implements NamedBean{
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;
    @NotEmpty
    @NotNull
    @Column(name="NAME")
    private String name;
    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name ="rabbi_id")
    private Rabbi rabbi;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{").append("id=").append(id).append(", name='").append(name).append( '\'');
        if (rabbi != null){
            sb.append(", rabbi=").append(rabbi.getName());
        }
        sb.append('}');
        return sb.toString();
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
