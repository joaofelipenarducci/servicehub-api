
package com.servicehub.servicehubapi.dto; // Define o pacote onde a classe está localizada.

import io.swagger.v3.oas.annotations.media.Schema; // Permite documentar os campos no Swagger.
import jakarta.validation.constraints.Email; // Valida se o e-mail possui um formato válido.
import jakarta.validation.constraints.NotBlank; // Impede valores nulos, vazios ou com apenas espaços.
import jakarta.validation.constraints.Size; // Define limites para o tamanho dos textos.

@Schema(description = "Dados recebidos para cadastrar ou atualizar um usuário") // Descreve o DTO no Swagger.
public class UserRequestDTO {

    @NotBlank(message = "O nome completo é obrigatório") // Exige que o nome seja informado.
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres") // Limita o tamanho do nome.
    @Schema(description = "Nome completo do usuário", example = "João Felipe") // Documenta o nome no Swagger.
    private String fullName;

    @NotBlank(message = "O e-mail é obrigatório") // Exige que o e-mail seja informado.
    @Email(message = "Informe um e-mail válido") // Verifica o formato do e-mail.
    @Size(max = 255, message = "O e-mail deve ter no máximo 255 caracteres") // Limita o tamanho do e-mail.
    @Schema(description = "E-mail do usuário", example = "joao@email.com") // Documenta o e-mail no Swagger.
    private String email;

    @NotBlank(message = "A senha é obrigatória") // Exige que a senha seja informada.
    @Size(min = 8, max = 255, message = "A senha deve ter entre 8 e 255 caracteres") // Define o tamanho permitido da senha.
    @Schema(description = "Senha do usuário", example = "Senha123") // Documenta a senha no Swagger.
    private String password;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres") // Limita o tamanho do telefone.
    @Schema(description = "Telefone do usuário", example = "(62) 99999-9999") // Documenta o telefone no Swagger.
    private String phone;

    @Size(max = 2000, message = "A biografia deve ter no máximo 2000 caracteres") // Limita o tamanho da biografia.
    @Schema(description = "Biografia do usuário", example = "Desenvolvedor de software") // Documenta a biografia no Swagger.
    private String bio;

    @Size(max = 500, message = "A URL do avatar deve ter no máximo 500 caracteres") // Limita o tamanho da URL.
    @Schema(description = "URL da imagem de perfil", example = "https://site.com/avatar.jpg") // Documenta a URL no Swagger.
    private String avatarUrl;

    public UserRequestDTO() { // Cria um construtor vazio para permitir a criação do DTO.
    }

    public String getFullName() { // Permite consultar o nome completo.
        return fullName; // Retorna o nome informado.
    }

    public void setFullName(String fullName) { // Permite definir o nome completo.
        this.fullName = fullName; // Armazena o nome recebido.
    }

    public String getEmail() { // Permite consultar o e-mail.
        return email; // Retorna o e-mail informado.
    }

    public void setEmail(String email) { // Permite definir o e-mail.
        this.email = email; // Armazena o e-mail recebido.
    }

    public String getPassword() { // Permite consultar a senha recebida na requisição.
        return password; // Retorna a senha para que o serviço possa codificá-la.
    }

    public void setPassword(String password) { // Permite definir a senha.
        this.password = password; // Armazena a senha recebida.
    }

    public String getPhone() { // Permite consultar o telefone.
        return phone; // Retorna o telefone informado.
    }

    public void setPhone(String phone) { // Permite definir o telefone.
        this.phone = phone; // Armazena o telefone recebido.
    }

    public String getBio() { // Permite consultar a biografia.
        return bio; // Retorna a biografia informada.
    }

    public void setBio(String bio) { // Permite definir a biografia.
        this.bio = bio; // Armazena a biografia recebida.
    }

    public String getAvatarUrl() { // Permite consultar a URL do avatar.
        return avatarUrl; // Retorna a URL informada.
    }

    public void setAvatarUrl(String avatarUrl) { // Permite definir a URL do avatar.
        this.avatarUrl = avatarUrl; // Armazena a URL recebida.
    }
}
