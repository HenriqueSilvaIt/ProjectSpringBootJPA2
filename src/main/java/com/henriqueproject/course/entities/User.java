package com.henriqueproject.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "tb_user")
public class User implements Serializable {
// Interface serializable é utiizado para que o objeto trafegue na rede, possa ser gravado em arquivo
    // pois tranforma o objeto em cadeias de byte
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Construtores
    public User() {

    }
    public User(Long id, String name, String email, String phone, String password) {
        this.id= id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;

    }
    @JsonIgnore
// quando tem relação entre 2 tabela a biblioteca de serialização fica chamando um outro sem parar ai é necessário colocar o JsonIgonore
    // em umas das tabela para parar o Looping) tem que  colocar em cima da lista ou atributo
    //Associação com a entitidade usuário, esse JsonIgonre, é colocado na classe user
    // porque ai vai ser feito a asociação da tabela Orders com User então vai pegar esse campo
    // abaixo client na API
    @OneToMany(mappedBy = "client") // aqui estamos fazendo a relação
    // no JPA de muitos para um onde a tabela cliente consegue acessar a tabela pedido
    //
  private List<Order> orders = new ArrayList<>();

// Getters  and se T
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email= email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone =phone;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass());
            return false;
    }
}
