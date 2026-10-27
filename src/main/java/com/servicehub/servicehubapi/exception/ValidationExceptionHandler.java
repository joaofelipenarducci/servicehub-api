
package com.servicehub.servicehubapi.exception; // Define o pacote da classe.

import org.springframework.http.HttpStatus; // Importa os status HTTP.
import org.springframework.http.ResponseEntity; // Permite montar respostas HTTP.
import org.springframework.web.bind.MethodArgumentNotValidException; // Representa erros de validação.
import org.springframework.web.bind.annotation.ExceptionHandler; // Permite tratar exceções.
import org.springframework.web.bind.annotation.RestControllerAdvice; // Trata erros dos controllers.

import java.util.LinkedHashMap; // Mantém a ordem dos campos do JSON.
import java.util.Map; // Permite armazenar os dados da resposta.

// Aplica este tratamento aos controllers da aplicação.
@RestControllerAdvice
public class ValidationExceptionHandler {

    // Trata erros quando os dados recebidos não passam pelas validações.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ) {
        // Cria o objeto que será retornado em JSON.
        Map<String, Object> response = new LinkedHashMap<>();

        // Adiciona o código HTTP ao corpo da resposta.
        response.put("status", HttpStatus.BAD_REQUEST.value());

        // Adiciona uma mensagem geral sobre o erro.
        response.put("error", "Erro de validação");

        // Cria um mapa para guardar os erros de cada campo.
        Map<String, String> errors = new LinkedHashMap<>();

        // Percorre todos os campos que falharam na validação.
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // Adiciona os erros específicos ao JSON.
        response.put("messages", errors);

        // Retorna HTTP 400 (Bad Request) com os erros encontrados.
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
