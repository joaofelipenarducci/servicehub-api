
package com.servicehub.servicehubapi.model; // Define o pacote da classe User.

import jakarta.persistence.Entity; // Permite mapear a classe para uma tabela.
import jakarta.persistence.GeneratedValue; // Permite gerar o ID automaticamente.
import jakarta.persistence.GenerationType; // Define a estratégia de geração do ID.
import jakarta.persistence.Id; // Identifica a chave primária.
import jakarta.persistence.Table; // Permite definir o nome da tabela.
import jakarta.persistence.Column; // Permite configurar as colunas do banco.

import jakarta.validation.constraints.Email; // Valida o formato do e-mail.
import jakarta.validation.constraints.NotBlank; // Impede campos vazios ou com espaços.
import jakarta.validation.constraints.Size; // Limita a quantidade de caracteres.

import org.hibernate.annotations.CreationTimestamp; // Preenche a data de criação.
import org.hibernate.annotations.UpdateTimestamp; // Atualiza a data de alteração.

import io.swagger.v3.oas.annotations.media.Schema; // Documenta os campos no Swagger.

import lombok.Getter; // Gera os métodos getters.
import lombok.Setter; // Gera os métodos setters.

import jakarta.persistence.Enumerated; // Define como o enum será armazenado no banco.
import jakarta.persistence.EnumType; // Define o formato de armazenamento do enum.

import java.time.LocalDateTime; // Representa data e hora.

// Gera automaticamente os getters e setters.
@Getter
@Setter
@Entity // Indica que a classe representa uma entidade do banco.
@Table(name = "users") // Define o nome da tabela.
public class User {

    @Id // Define o campo como chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o ID.
    @Schema(description = "Identificador único do usuário", example = "1")
    private Long id; // Identificador do usuário.

    @NotBlank(message = "O nome completo é obrigatório") // Exige um nome preenchido.
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres") // Limita o nome.
    @Column(name = "full_name", nullable = false, length = 100)
    @Schema(description = "Nome completo do usuário", example = "João Felipe")
    private String fullName; // Nome completo.

    @NotBlank(message = "O e-mail é obrigatório") // Exige um e-mail preenchido.
    @Email(message = "O e-mail informado é inválido") // Valida o formato do e-mail.
    @Size(max = 255, message = "O e-mail deve ter no máximo 255 caracteres") // Limita o e-mail.
    @Column(nullable = false, unique = true, length = 255)
    @Schema(description = "E-mail do usuário", example = "joao@email.com")
    private String email; // E-mail do usuário.

    @NotBlank(message = "A senha é obrigatória") // Exige uma senha preenchida.
    @Size(min = 8, max = 255, message = "A senha deve ter entre 8 e 255 caracteres")
    @Column(name = "password_hash", nullable = false, length = 255)
    @Schema(
            description = "Senha do usuário armazenada de forma criptografada",
            example = "senhaCriptografada"
    )
    private String passwordHash; // Deve armazenar o hash da senha, não a senha pura.

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    @Column(length = 20)
    @Schema(description = "Telefone do usuário", example = "(62) 99999-9999")
    private String phone; // Telefone do usuário.

    @Size(max = 2000, message = "A biografia deve ter no máximo 2000 caracteres")
    @Column(columnDefinition = "TEXT")
    @Schema(description = "Biografia do usuário", example = "Desenvolvedor de software")
    private String bio; // Biografia do usuário.

    @Size(max = 500, message = "A URL do avatar deve ter no máximo 500 caracteres")
    @Column(name = "avatar_url", length = 500)
    @Schema(
            description = "URL da imagem de perfil",
            example = "https://site.com/avatar.jpg"
    )
    private String avatarUrl; // Endereço da imagem de perfil.

    @Column(name = "is_active") // Define a coluna que indica se o usuário está ativo.
    @Schema(description = "Indica se o usuário está ativo", example = "true")
    private Boolean active; // Indica se a conta está ativa.

    @CreationTimestamp // Preenche automaticamente a data de criação.
    @Column(name = "created_at")
    @Schema(description = "Data de criação do usuário", example = "2026-09-12T16:58:03")
    private LocalDateTime createdAt; // Data e hora de criação.

    @UpdateTimestamp // Atualiza automaticamente a data de alteração.
    @Column(name = "updated_at")
    @Schema(description = "Data da última atualização", example = "2026-09-12T17:10:00")
    private LocalDateTime updatedAt; // Data e hora da última alteração.
}
