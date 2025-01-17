package com.henriqueproject.course.resources;

import org.springframework.web.bind.annotation.DeleteMapping;
import com.henriqueproject.course.entities.User;
import com.henriqueproject.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        // Para dizer quesse objeto user vai chegar no modo JSON na api na hora de fazer requisição
        // esse JSON vai ser deserializado para um objeto user tem que colocar um RequestBody
        // no caso de inserção

        obj = service.insert(obj);
        /*return ResponseEntity.ok().body(obj); Esse .ok vai retornar na API o 200
        se for tudo ok no insert do JSON, porém o ideal para criação de objeto é o  retorno
        201 que vamos fazer abaixo:
         */

        // No padrão http quando vocÊ vai fazer
        // retornar um 201 é esperado que a respota retorne um cabeçalho
        // contem um location (local) para gerar o endereço utiliizamos a variavel abaixo:
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);

        // agora sim ele vai dar 201 que é o created
        // ai se você mudar para get o mesmo objet na postman ele vai trazer o objeto em JSON certinho
        // poir o location vai estar inserido na API

    }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> delete (@PathVariable Long id) { // Para Long ID ser reconhecido como uma variavel
            // vamos colocar o @PathVariable
            // como é só deleção não retorna nada é void
            service.delete(id);
            return ResponseEntity.noContent().build(); // o no content é para tratar
            // reposta que não saida ou seja respota vazia e o código para
            // tratar respota que n tem saída no http é o código 204
        }
    }

