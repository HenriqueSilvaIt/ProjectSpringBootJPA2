package com.henriqueproject.course.config;

import com.henriqueproject.course.entities.User;
import com.henriqueproject.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // indica para o spring que é uma classe de configuração
@Profile("test") // para dizer que é uma comnfiguração espefica para o perfil criado "test
public class TestConfig implements CommandLineRunner {
    // Esse CommandLineRunner implementa o método run abaixo faz
    // tudo que tiver dentro seja executado quando o programa é iniciado

    //Fazendo injeção de dependência
    @Autowired
    private UserRepository userRepository; // atributo
    // nesse caso n precisa criar uma interface genérica para fazer a injeção
    // de dependencia com o Autowired o Spring faz injeção automatica

    @Override
    public void run(String... args) throws Exception { // tudo que tiver dentro desse método
        // vai ser executado quando a aplicação for iniciada
        User u1 = new User(null, "Tauana Mireli", "tauana@gmail.com", "99943543", "23423432");
        User u2 = new User(null, "Henrique Silva", "henrique@gmail.com", "1231231231", "234234234");
    // id é nulo porque ele é implementado automaticamente pelo banco de dados

        //para salvar esses usuários acima no banco de dados:
        userRepository.saveAll(Arrays.asList(u1,u2)); // salvando usuários do objeto no banco
    }


}

