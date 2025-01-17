package com.henriqueproject.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")  // aqui você coloca o nome da variavel que você
    // colocou como lista ou conjunto na outra classe (product) que está associada a essa no caso
    // foi declarado private Set<Category> categories = new HashSet<>();, por isso passamos categories
    // como argumento
    // Associação Category x Product
    private Set<Product> products = new HashSet<>();
// precisa incluir o get para coleção acima, só oget



    // Construtores
    public Category() {

    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
