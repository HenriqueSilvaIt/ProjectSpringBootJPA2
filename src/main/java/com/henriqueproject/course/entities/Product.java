package com.henriqueproject.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //Associações Produto x Categories

    @ManyToMany // Faz associação muito para muitos
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"), // informe qual vai ser o nome da chave estrangeira da tabela dessa classe
            inverseJoinColumns = @JoinColumn(name = "category_id")) // informe qual vai ser o nome da chave estrangeira da tabela da outra classe que vocÊ quer fazer associação
    // JoinTable cria uma tabela de associação unificando as duas tabelas,
    // nesse caso, passamos qual é o nome da tabela de associação,
    // e com o joinColumns quais vão serão as chaves estrangeira
    // que vai associar a  tabela produto co  a tabela de categoria
    // inverseJoinColumns é para definir a chave estrangeira da outra classe, ness caso da classe category
    private Set<Category> categories = new HashSet<>();// o mesmo produto não pode ter uma categoria mais de uma vez por isso usamo o set
    // Nós instaciamos o new hash para não iniciar vazia, vai iniciar valendo nula


    // Construtores
    public Product() {

    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // category não entra no construtor porque é coloção
        // e já está sendo instanciada ali em cima


    //Get
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
