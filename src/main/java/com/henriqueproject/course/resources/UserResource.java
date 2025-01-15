package com.henriqueproject.course.resources;

import com.henriqueproject.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /* Isso é para informar para Spring  que essa classe
é um recurso Web que é  implementado
em cima de um contralador REST */
@RequestMapping(value= "/users") /*  Para dar um nome para o recurso acima
é só colocar o request Mapping, ai colocamos valor= /caminhodoseurecurso*/

public class UserResource {
    // Método endpoint para acessar os usuários, ResponseEntity é um tipo
    // específico do Spring para retornar respostas de requisições Web

    @GetMapping // Indica que responde a requisição do tipo GET do HTTP
    public ResponseEntity<User>  findAll() {
        // o tipo é a classe User <>, importante importa a classe
        User u = new User(1L, "Maria", "maria@gmail.com", "999999", "123223");
                // tem que colocar o L no id porque é Long e o java exige isso
        return ResponseEntity.ok().body(u); // ResponseEntity para retornar a resposta
        // body() para retornar o corpo dessa resposta e passa o objeto como argumento
    }
}
