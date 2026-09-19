package com.servicehub.servicehub_api;

// Importa o código HTTP, como 404 Not Found
import org.springframework.http.HttpStatus;

// Permite definir qual método será executado quando ocorrer uma exceção
import org.springframework.web.bind.annotation.ExceptionHandler;

// Permite definir o status HTTP retornado pela API
import org.springframework.web.bind.annotation.ResponseStatus;

// Permite tratar erros de todos os Controllers da aplicação
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Indica que esta classe tratará exceções globalmente
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Define que este método tratará erros do tipo RuntimeException
    @ExceptionHandler(RuntimeException.class)

    // Retorna o status HTTP 404 quando ocorrer esse erro
    @ResponseStatus(HttpStatus.NOT_FOUND)

    // Método responsável por tratar a exceção
    public String handleRuntimeException(RuntimeException exception) {

        // Retorna a mensagem do erro, por exemplo: "Usuário não encontrado"
        return exception.getMessage();
    }
}