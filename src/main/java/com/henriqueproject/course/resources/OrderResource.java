package com.henriqueproject.course.resources;

import com.henriqueproject.course.entities.Order;
import com.henriqueproject.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController /* Isso é para informar para Spring  que essa classe
é um recurso Web que é  implementado
em cima de um contralador REST */
@RequestMapping(value= "/orders") /*  Para dar um nome para o recurso acima
é só colocar o request Mapping, ai colocamos valor= /caminhodoseurecurso*/
public class OrderResource {
    // Método endpoint para acessar os usuários, ResponseEntity é um tipo
    // específico do Spring para retornar respostas de requisições Web

    // colocando dependência para o service
    @Autowired
    private OrderService service;
    // para funcionar essa classe OrderRepository precisa estar registrado como um componente do Spring

    @GetMapping // Indica que responde a requisição do tipo GET do HTTP
    public ResponseEntity<List< Order>>  findAll() { // fica lista de Order porque é uma lista de usuário
        // o tipo é a classe Order <>, importante importa a classe

        List<Order> list = service.findAll(); // essa lista pega o método findAll da classe OrderService
                // tem que colocar o L no id porque é Long e o java exige isso
        return ResponseEntity.ok().body(list); // ResponseEntity para retornar a resposta
        // body() para retornar o corpo dessa resposta e passa o objeto como argumento
    }

    //Buscando usuário por id
    @GetMapping(value = "/{id}") // isso indica que a requisição vai aceitar um Id dentro da url
    public ResponseEntity<Order> findByid(@PathVariable long id) {//para o spring
        // aceitar esse id e entender que ele é o parâmetro que vai chegar na url
        // tem que colocar @PathVariable
        Order Order = service.findById(id);
        return ResponseEntity.ok().body(Order); // criamos o nosso end point
    }
}
