
package com.servicehub.servicehubapi.config; // Define o pacote da classe.

import io.swagger.v3.oas.models.Components; // Permite registrar componentes reutilizáveis na documentação.
import io.swagger.v3.oas.models.ExternalDocumentation; // Permite adicionar links para documentação externa.
import io.swagger.v3.oas.models.OpenAPI; // Representa a configuração principal do OpenAPI.
import io.swagger.v3.oas.models.info.Contact; // Permite definir informações de contato.
import io.swagger.v3.oas.models.info.Info; // Permite configurar as informações gerais da API.
import io.swagger.v3.oas.models.info.License; // Permite definir a licença da API.
import io.swagger.v3.oas.models.security.SecurityRequirement; // Associa um esquema de segurança à API.
import io.swagger.v3.oas.models.security.SecurityScheme; // Define o tipo de autenticação utilizado.
import org.springframework.context.annotation.Bean; // Permite registrar objetos no Spring.
import org.springframework.context.annotation.Configuration; // Indica uma classe de configuração.

@Configuration // Informa ao Spring que esta classe contém configurações.
public class OpenApiConfig {

    @Bean // Registra a configuração personalizada do OpenAPI no Spring.
    public OpenAPI customOpenAPI() {

        return new OpenAPI() // Cria a configuração principal da documentação da API.
                .info(new Info() // Define as informações gerais exibidas no Swagger.
                        .title("ServiceHub API") // Define o nome da API.
                        .version("1.0") // Informa a versão atual da API.
                        .description("Documentação da API do sistema ServiceHub") // Descreve a API.
                        .contact(new Contact() // Define os dados de contato da equipe.
                                .name("Equipe ServiceHub") // Informa o nome da equipe responsável.
                                .email("servicehub@email.com")) // Informa o e-mail de contato.
                        .license(new License() // Define a licença de uso da API.
                                .name("Apache 2.0") // Informa o nome da licença.
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))) // Informa o endereço da licença.

                .externalDocs(new ExternalDocumentation() // Adiciona documentação externa.
                        .description("Documentação externa do projeto") // Descreve o link externo.
                        .url("https://springdoc.org/")) // Define o endereço da documentação do Springdoc.

                .components(new Components() // Registra os componentes de segurança.
                        .addSecuritySchemes("basicAuth", // Cria o esquema de autenticação chamado basicAuth.
                                new SecurityScheme() // Define as configurações da autenticação.
                                        .type(SecurityScheme.Type.HTTP) // Utiliza autenticação do tipo HTTP.
                                        .scheme("basic"))) // Configura o HTTP Basic, que utiliza usuário e senha.

                .addSecurityItem(new SecurityRequirement() // Associa o esquema de segurança à documentação da API.
                        .addList("basicAuth")); // Informa que as operações documentadas utilizam o esquema basicAuth.
    }
}
