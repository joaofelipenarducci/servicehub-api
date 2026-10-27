package com.servicehub.servicehubapi.config; // Define o pacote da classe.

import org.springframework.context.annotation.Bean; // Permite registrar objetos no Spring.
import org.springframework.context.annotation.Configuration; // Indica uma classe de configuração.
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Permite configurar a segurança HTTP.
import org.springframework.security.web.SecurityFilterChain; // Define a cadeia de filtros de segurança.
import org.springframework.security.core.userdetails.User; // Permite criar um usuário.
import org.springframework.security.core.userdetails.UserDetails; // Representa os dados de um usuário.
import org.springframework.security.core.userdetails.UserDetailsService; // Define como os usuários são carregados.
import org.springframework.security.provisioning.InMemoryUserDetailsManager; // Armazena usuários em memória.
import org.springframework.security.crypto.password.PasswordEncoder; // Permite codificar e verificar senhas.
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // Habilita a segurança por anotações nos métodos.

@Configuration // Informa ao Spring que esta classe contém configurações.
@EnableMethodSecurity // Habilita a verificação das permissões definidas com @PreAuthorize.
public class SecurityConfig {


    @Bean // Registra a configuração de segurança no Spring.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Desativa a proteção CSRF para facilitar os testes da API.
                .authorizeHttpRequests(auth -> auth // Define as regras de acesso às rotas.
                        .requestMatchers(
                                "/swagger-ui/**", // Permite acessar a interface do Swagger sem autenticação.
                                "/v3/api-docs/**" // Permite acessar a documentação da API sem autenticação.
                        ).permitAll() // Libera as rotas listadas acima.
                        .anyRequest().authenticated() // Exige autenticação nas demais rotas.
                )
                .httpBasic(basic -> {}); // Habilita a autenticação HTTP Basic.

        return http.build(); // Constrói e retorna a configuração de segurança.
    }

    @Bean // Registra o serviço de usuários no Spring.
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // Cria o usuário administrador com o nome "admin".
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123")) // Codifica a senha utilizando o BCrypt configurado.
                .roles("ADMIN") // Define o usuário como administrador, permitindo operações restritas a essa função.
                .build(); // Finaliza a criação do usuário administrador.

        // Cria um usuário comum com o nome "usuario".
        UserDetails usuario = User.withUsername("usuario")
                .password(passwordEncoder.encode("user12345")) // Codifica a senha utilizando o BCrypt configurado.
                .roles("USER") // Define o usuário como comum, permitindo apenas operações autorizadas para essa função.
                .build(); // Finaliza a criação do usuário comum.

        return new InMemoryUserDetailsManager(admin, usuario); // Armazena os dois usuários em memória para autenticação.
    }
}