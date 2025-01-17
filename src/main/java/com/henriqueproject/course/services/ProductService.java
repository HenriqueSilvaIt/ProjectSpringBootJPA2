package com.henriqueproject.course.services;

import com.henriqueproject.course.entities.Product;
import com.henriqueproject.course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra classe como componete do spring
public class ProductService {
    // Nessa classe vai ter uma operação para buscar todos usuários e o usuário pelo Id dele

    @Autowired // injeção de dependencia automatica de forma transapante ao programador

    private ProductRepository repository;


    // Operação da camada do serviço findAll que repasse para camada de repository findAll
    // e pega todos os usuários do banco
    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj =  repository.findById(id);
        return obj.get(); //Esse optional retorna com o get o objeto que está dentro desse
        // optinal para apontarmos que o respository vai encontratar
        //o Id do objeto Product(da do pacote entites)
    }

}
