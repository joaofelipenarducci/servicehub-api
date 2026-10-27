package com.servicehub.servicehubapi.config; // Define o pacote da classe.

import org.springframework.context.annotation.Bean; // Permite registrar objetos no Spring.
import org.springframework.context.annotation.Configuration; // Indica uma classe de configuração.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Implementa a codificação BCrypt.
import org.springframework.security.crypto.password.PasswordEncoder; // Interface para codificação e verificação de senhas.

@Configuration // Indica que esta classe contém configurações.
public class PasswordConfig {

    @Bean // Disponibiliza o codificador de senhas para a aplicação.
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(); // Retorna o codificador BCrypt.
    }
}