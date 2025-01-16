package com.henriqueproject.course.config;

import com.henriqueproject.course.entities.Category;
import com.henriqueproject.course.entities.Order;
import com.henriqueproject.course.entities.User;
import com.henriqueproject.course.entities.enums.OrderStatus;
import com.henriqueproject.course.repositories.CategoryRepository;
import com.henriqueproject.course.repositories.OrderRepository;
import com.henriqueproject.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception { // tudo que tiver dentro desse método
        // vai ser executado quando a aplicação for iniciada


        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        User u1 = new User(null, "Tauana Mireli", "tauana@gmail.com", "99943543", "23423432");
        User u2 = new User(null, "Henrique Silva", "henrique@gmail.com", "1231231231", "234234234");
    // id é nulo porque ele é implementado automaticamente pelo banco de dados

        // Aqui está fazendo uma associação, pois no final colocamos u1 e u2 que são os objetos
        // como temos um atributo do tipo user na classe Order, alem da lista order
        // abaixo quando instanciamos temos que passa esse objeto user no final
        // que ai ele vai fazer a associação entre os objetos
        Order o1 = new Order(null, Instant.parse("2024-05-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2024-07-21T03:42:01Z"), OrderStatus.WAITING_PAYMENT,u2);
        Order o3 = new Order(null, Instant.parse("2024-08-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);




        //para salvar esses usuários acima no banco de dados:
        userRepository.saveAll(Arrays.asList(u1,u2)); // salvando usuários do objeto no banco
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));
    }


}

