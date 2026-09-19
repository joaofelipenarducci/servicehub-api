package com.servicehub.servicehubapi.model; // Define o pacote onde a classe User está localizada.

import jakarta.persistence.Entity; // Permite transformar a classe em uma entidade do banco de dados.
import jakarta.persistence.GeneratedValue; // Permite gerar valores automaticamente.
import jakarta.persistence.GenerationType; // Define a estratégia de geração do ID.
import jakarta.persistence.Id; // Identifica o campo que será a chave primária.
import jakarta.persistence.Table; // Permite definir o nome da tabela no banco.
import jakarta.persistence.Column; // Permite configurar as colunas da tabela.

import org.hibernate.annotations.CreationTimestamp; // Preenche automaticamente a data de criação.
import org.hibernate.annotations.UpdateTimestamp; // Atualiza automaticamente a data de alteração.

import io.swagger.v3.oas.annotations.media.Schema; // Permite documentar os campos no Swagger.

import lombok.Getter; // Gera automaticamente os métodos getters.
import lombok.Setter; // Gera automaticamente os métodos setters.

@Getter // Cria os métodos para consultar os valores dos atributos.
@Setter // Cria os métodos para alterar os valores dos atributos.
@Entity // Informa que esta classe representa uma tabela do banco de dados.
@Table(name = "users") // Define que a classe será relacionada à tabela users.
public class User { // Declara a classe que representa um usuário.

    @Id // Define o campo id como chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz o banco gerar o ID automaticamente.
    @Schema(description = "Identificador único do usuário", example = "1") // Documenta o ID no Swagger.
    private Long id; // Armazena o identificador único do usuário.

    @Column(name = "full_name", nullable = false, length = 100) // Define o nome, obrigatoriedade e tamanho da coluna.
    @Schema(description = "Nome completo do usuário", example = "João Felipe") // Documenta o nome no Swagger.
    private String fullName; // Armazena o nome completo do usuário.

    @Column(nullable = false, unique = true, length = 255) // Torna o e-mail obrigatório, único e limita seu tamanho.
    @Schema(description = "E-mail do usuário", example = "joao@email.com") // Documenta o e-mail no Swagger.
    private String email; // Armazena o e-mail do usuário.

    @Column(name = "password_hash", nullable = false, length = 255) // Define a coluna da senha criptografada.
    @Schema(
            description = "Senha do usuário armazenada de forma criptografada", // Explica o campo no Swagger.
            example = "senhaCriptografada" // Mostra um exemplo de valor.
    )
    private String passwordHash; // Armazena a senha protegida do usuário.

    @Column(length = 20) // Define que o telefone pode ter até 20 caracteres.
    @Schema(description = "Telefone do usuário", example = "(62) 99999-9999") // Documenta o telefone no Swagger.
    private String phone; // Armazena o telefone do usuário.

    @Column(columnDefinition = "TEXT") // Define a coluna como texto, permitindo uma descrição maior.
    @Schema(description = "Biografia do usuário", example = "Desenvolvedor de software") // Documenta a biografia no Swagger.
    private String bio; // Armazena a biografia do usuário.

    @Column(name = "avatar_url", length = 500) // Define o nome da coluna e o limite de caracteres da URL.
    @Schema(
            description = "URL da imagem de perfil", // Explica o campo no Swagger.
            example = "https://site.com/avatar.jpg" // Mostra um exemplo de URL.
    )
    private String avatarUrl; // Armazena o endereço da imagem de perfil.

    @Column(name = "is_active") // Define o nome da coluna que indica se o usuário está ativo.
    @Schema(description = "Indica se o usuário está ativo", example = "true") // Documenta o status do usuário no Swagger.
    private Boolean active; // Armazena true para ativo ou false para inativo.

    @CreationTimestamp // Preenche automaticamente a data e hora de criação do usuário.
    @Column(name = "created_at") // Define a coluna que armazena a data de criação.
    @Schema(
            description = "Data de criação do usuário", // Explica o campo no Swagger.
            example = "2026-09-12T16:58:03" // Mostra um exemplo de data.
    )
    private java.time.LocalDateTime createdAt; // Armazena a data e hora de criação do usuário.

    @UpdateTimestamp // Atualiza automaticamente a data e hora da última alteração.
    @Column(name = "updated_at") // Define a coluna que armazena a data de atualização.
    @Schema(
            description = "Data da última atualização", // Explica o campo no Swagger.
            example = "2026-09-12T17:10:00" // Mostra um exemplo de data.
    )
    private java.time.LocalDateTime updatedAt; // Armazena a data e hora da última atualização.

} // Finaliza a classe User.