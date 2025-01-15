package com.henriqueproject.course.resources;

import com.henriqueproject.course.entities.User;
import com.henriqueproject.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController /* Isso é para informar para Spring  que essa classe
é um recurso Web que é  implementado
em cima de um contralador REST */
@RequestMapping(value= "/users") /*  Para dar um nome para o recurso acima
é só colocar o request Mapping, ai colocamos valor= /caminhodoseurecurso*/
public class UserResource {
    // Método endpoint para acessar os usuários, ResponseEntity é um tipo
    // específico do Spring para retornar respostas de requisições Web

    // colocando dependência para o service
    @Autowired
    private UserService service;
    // para funcionar essa classe UserRepository precisa estar registrado como um componente do Spring

    @GetMapping // Indica que responde a requisição do tipo GET do HTTP
    public ResponseEntity<List< User>>  findAll() { // fica lista de user porque é uma lista de usuário
        // o tipo é a classe User <>, importante importa a classe

        List<User> list = service.findAll(); // essa lista pega o método findAll da classe userService
                // tem que colocar o L no id porque é Long e o java exige isso
        return ResponseEntity.ok().body(list); // ResponseEntity para retornar a resposta
        // body() para retornar o corpo dessa resposta e passa o objeto como argumento
    }

    //Buscando usuário por id
    @GetMapping(value = "/{id}") // isso indica que a requisição vai aceitar um Id dentro da url
    public ResponseEntity<User> findByid(@PathVariable long id) {//para o spring
        // aceitar esse id e entender que ele é o parâmetro que vai chegar na url
        // tem que colocar @PathVariable
        User user = service.findById(id);
        return ResponseEntity.ok().body(user); // criamos o nosso end point
    }
}
