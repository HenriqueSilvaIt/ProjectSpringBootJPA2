package com.henriqueproject.course.services;

import com.henriqueproject.course.entities.User;
import com.henriqueproject.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // registra classe como componete do spring
public class UserService {
    // Nessa classe vai ter uma operação para buscar todos usuários e o usuário pelo Id dele

    @Autowired // injeção de dependencia automatica de forma transapante ao programador
    private UserRepository repository;


    // Operação da camada do serviço findAll que repasse para camada de repository findAll
    // e pega todos os usuários do banco
    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj =  repository.findById(id);
        return obj.get(); //Esse optional retorna com o get o objeto que está dentro desse
        // optinal para apontarmos que o respository vai encontratar
        //o Id do objeto user( do pacote entities)
    }

    //Inserir novo usuário cliente no banco
    public User insert(User obj) {
        return repository.save(obj); // esse save por padrão já retorna o objeto salvo do tipo user
    }

    // Deletar usuário do Banco
    public void delete(Long id) {
       repository.deleteById(id);
    }

    // Atualizar usuário
    public User update(Long id, User obj) { // ele recebe um Id para pegar
        // qual o usuário será atualizado e o obj User para dizer quais informações serão atualizadas
        // incluive da pra usar esse método também para realizar o pagamento
        User entity = repository.getReferenceById(id); // ReferenceById vai instanciar um objeto
        // monitorado pelo JPA e não vai colocar no banco de dados ainda
        // eu vou poder mexer nele e em seguida eu salvar no banco de dados depoi
        updateData(entity, obj); // atualiza o atributo entity que recebe o id
        // baseado nos dados do obj(objeto TEM QUE CRIAR O MÉTODO RECOMENDADO
        // do updateDate , só pasar o mouse em cima que ele criar automaticamente
        return repository.save(entity); // salva no banco de ados

    }

        // Esse método abaixo vai pedir para informar quais dados vocÊ vai atualizar
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());// Atualiza nome
        entity.setEmail(obj.getEmail()); // atualiza email
        entity.setPhone(obj.getPhone()); // atualiza telefone

        // IMPORNTANTE NESSE CASO NÃO COLOCAMOS  ID e SENHA
        // PARA SER ATUALIZADO
        // SOMENTE INFORMAÇÕES CADASTRAIS

    }

}
