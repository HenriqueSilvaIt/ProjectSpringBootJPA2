package com.henriqueproject.course.config;

import com.henriqueproject.course.entities.Category;
import com.henriqueproject.course.entities.Order;
import com.henriqueproject.course.entities.Product;
import com.henriqueproject.course.entities.User;
import com.henriqueproject.course.entities.enums.OrderStatus;
import com.henriqueproject.course.repositories.CategoryRepository;
import com.henriqueproject.course.repositories.OrderRepository;
import com.henriqueproject.course.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception { // tudo que tiver dentro desse método
        // vai ser executado quando a aplicação for iniciada


        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");
        Category cat4 = new Category(null, "Beauty");

        Product p1 = new Product(null, "The Lord of Rings", "Lorem impsum dolor sit amet, consectetur.", 90.5,"");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "Base Tauana Beauty", "Base de maquiagem.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        // salvando inserts no banc
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4 ,p5));

        //Associando produto a categoria
        p1.getCategories().add(cat2);
        p4.getCategories().add(cat4);
        //p2 é um produto que faz parte de 2 categoria
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        // Salvando produtos com as associações feita em memória
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
        // Essa é a forma feita em JPA é diferente de fazer em SQL

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

