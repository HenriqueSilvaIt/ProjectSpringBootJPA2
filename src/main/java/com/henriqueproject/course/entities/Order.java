package com.henriqueproject.course.entities;

import com.henriqueproject.course.entities.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.henriqueproject.course.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order") // já existe uma operação order no H2 por isso que da conflito
// colocar o nome da tabela de order
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Essa formatação formata o JSON da data moment
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment; // a partir do Java 8 surgiu o instant que é melhor que a classe
    // Date

    private Integer orderStatus;


    @ManyToOne // implementando relação no banco de dados
    // isso vai fazer o JPA criar uma chave extrangeira lá na tabela Usuário
    @JoinColumn(name = "client_id") // Aqui você passa o nome da chave estrangeira
    private User client;

    @OneToMany(mappedBy = "id.order") // No ordem item eu tenho o ID e o ID é que tem o pedido
    // ou seja é no ID que do OrderItem que eu tenho orderItemPk QUE TEm pedido
    // que a junção das tabela product e category
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) //
     // Aqui estamos mapeando mapeando  as entidade com mesmo ID
    //por isso estamos colocando desse cascade
    private Payment payment;

    // Construtores
    public Order() {
    }
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus); // para fazer a atribuição do OrderStatus ao objeto
        this.client = client;
    }

    // Get

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getMoment(){
        return moment;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus); // como o da classe agora é Integer
        // vamos converter usando o return acima para pegar o tipo OrderStatus
        // que criamos no enum
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {// para evitar que o programador passe o valor nulo
            // para consutruir o objeto
            this.orderStatus = orderStatus.getCode();
        }
    }
    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items; // get do atributo Set<OrdemItem>
    }

// Hash code e equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
