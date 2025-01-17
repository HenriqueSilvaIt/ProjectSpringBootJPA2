package com.henriqueproject.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    // essa RuntimeException é a exceção que o compilador não te obriga tratar
    private static final long serialVersionUID = 1L;


    // aqui vou passar o ID do objeto que tentei encontrar e não achei
    public ResourceNotFoundException(Object id ) {
        super("Rsource not found: Id " + id); // Mensagem padrão para recurso de ID não encontrado

    }

}
