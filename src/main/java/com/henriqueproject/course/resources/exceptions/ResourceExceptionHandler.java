package com.henriqueproject.course.resources.exceptions;

import com.henriqueproject.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class) // Aqui usamos esse anotion para passar o nome da execeção que vamos
    // estar interceptando e que esse método  abaixo vai pegar para trata, tem colocar o .class para pegar na classe
    // qualquer exceção que for lançada com esse argumento ele vai fazer o tratamento abaixo
    // aqui em baixo nós passamos o nome da classe standError como objeto de resposta
    public ResponseEntity<StandardError> resoruceNotFoun(ResourceNotFoundException e, HttpServletRequest request) {
    // primeiro passamos uma exceção do tipo personalizada e depois esse HttpServletRequest
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; // que representa o http 404 que é not found
        StandardError err = new StandardError(Instant.now(), status.value(), error,
                e.getMessage(), request.getRequestURI());
        // Instant.Now( é para pegar o Instant em que o erro ocorreu
        // status é para pegar mensagem do http como não existe no construrtor do objeto
        // o HttpStatus é necessário colocar o .value() para ele passar par ainteiror
        // error é o que colocamos no atributo acima
        // e e.getMessage é o primeiro argumento do método que pega nossa exceção personalizada
        // e o caminho é o .getRequestURI é o que pega informação do http request que passamos
        // como o segundo parâmetro dese método
        return ResponseEntity.status(status).body(err); // para retornar uma mensagem personalizada
        // e no corpo da mensagem tarzer o erro
    }

}
